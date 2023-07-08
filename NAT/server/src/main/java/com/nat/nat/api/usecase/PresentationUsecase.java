package com.nat.nat.api.usecase;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nat.nat.api.repository.interfaces.PaperRepositoryInterfaces;
import com.nat.nat.api.repository.interfaces.PresentationRepositoryInterfaces;
import com.nat.nat.api.repository.interfaces.SlideRepositoryInterfaces;
import com.nat.nat.api.repository.interfaces.StudentRepositoryInterfaces;
import com.nat.nat.api.usecase.interfaces.PresentationUsecaseInterfaces;
import com.nat.nat.entity.Paper;
import com.nat.nat.entity.Presentation;
import com.nat.nat.entity.Slide;
import com.nat.nat.entity.Student;
import com.nat.nat.entity.Token;
import com.nat.nat.lib.auth.TokenManager;
import com.nat.nat.lib.utils.StringOperator;

@Service
public class PresentationUsecase implements PresentationUsecaseInterfaces {
    private final PaperRepositoryInterfaces paperRepo;
    private final SlideRepositoryInterfaces slideRepo;
    private final PresentationRepositoryInterfaces presentationRepo;
    private final StudentRepositoryInterfaces studentRepo;

    public PresentationUsecase(PaperRepositoryInterfaces paperRepo, SlideRepositoryInterfaces slideRepo, PresentationRepositoryInterfaces presentationRepo, StudentRepositoryInterfaces studentRepo) {
        this.paperRepo = paperRepo;
        this.slideRepo = slideRepo;
        this.presentationRepo = presentationRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public ResponseEntity<?> create(List<String> headers, MultipartFile paper, MultipartFile slide, String title, String date, String note) {
        StringOperator so = new StringOperator();
        TokenManager tm = new TokenManager("example");
        String tk = tm.getTokenFromHeaders(headers);
        tm.setToken(tk);
        Token token = tm.decodeToken();
        String studentId = token.userId;
        try {
            byte[] bodyOfPaper = paper.getBytes();
            String nameOfPaper = paper.getName();
            byte[] bodyOfSlide = slide.getBytes();
            String nameOfSlide = slide.getName();

            Paper newPaper = new Paper(nameOfPaper, bodyOfPaper);
            Slide newSlide = new Slide(nameOfSlide, bodyOfSlide);

            Paper createdPaper = this.paperRepo.create(newPaper);
            Slide createdSlide = this.slideRepo.create(newSlide);

            Student student = this.studentRepo.getByStudentId(studentId);

            Presentation newPresentation = new Presentation(student.getId(), title, so.convertIsoToLocalDateTime(date), note);
            newPresentation.setPaperId(createdPaper.getId());
            newPresentation.setSlideId(createdSlide.getId());
            Presentation createdPresentation = this.presentationRepo.create(newPresentation);
            return new ResponseEntity<>(createdPresentation, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getListWithQuery() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
