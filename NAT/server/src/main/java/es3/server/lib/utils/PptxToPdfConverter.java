package es3.server.lib.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PptxToPdfConverter {
    public static byte[] convert(byte[] pptxData) throws IOException {
        ByteArrayInputStream pptxStream = new ByteArrayInputStream(pptxData);
        XMLSlideShow    ppt = new XMLSlideShow(pptxStream);
        pptxStream.close();

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

        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        pdf.save(pdfOutput);
        pdf.close();
        ppt.close();

        byte[] pdfByteArray = pdfOutput.toByteArray();
        pdfOutput.close();

        return pdfByteArray;

    }
}
