package com.nat.nat.lib.utils;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PptxToPdfConverter {
    public void convert(String pptxPath, String pdfPath) throws IOException {
        XMLSlideShow    ppt = new XMLSlideShow(new FileInputStream(pptxPath));
        PDDocument      pdf = new PDDocument();

        for (XSLFSlide slide : ppt.getSlides()) {
            Dimension   pageSize = ppt.getPageSize();
            PDPage      pdfPage  = new PDPage(new PDRectangle(pageSize.width, pageSize.height));
            pdf.addPage(pdfPage);
            
            PDPageContentStream contentStream = new PDPageContentStream(pdf, pdfPage);

            BufferedImage   bImg     = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D      graphics = bImg.createGraphics();
            slide.draw(graphics);

            PDImageXObject pdImage = LosslessFactory.createFromImage(pdf, bImg);

            contentStream.drawImage(pdImage, 0, 0, pdfPage.getMediaBox().getWidth(), pdfPage.getMediaBox().getHeight());

            contentStream.close();
        }

        pdf.save(new FileOutputStream(pdfPath));

        ppt.close();
        pdf.close();
    }
}
