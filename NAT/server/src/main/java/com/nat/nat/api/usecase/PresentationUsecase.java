package com.nat.nat.api.usecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.nat.nat.lib.auth.TokenManager;
import com.nat.nat.lib.utils.StringOperator;
import com.nat.nat.lib.utils.TimeOperator;
import com.nat.nat.rules.Token;

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
        TimeOperator to = new TimeOperator();
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
            String term = to.extractTermFromDate(so.convertIsoToLocalDateTime(date));
            newPresentation.setTerm(term);
            newPresentation.setDegree(student.getGrade());
            Presentation createdPresentation = this.presentationRepo.create(newPresentation);
            return new ResponseEntity<>(createdPresentation, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getListWithQuery(List<String> queries) {
        TimeOperator tm = new TimeOperator();
        List<Presentation> presentations = this.presentationRepo.getWithQuery(queries);
        List<GetListWithQueryResponse> res = new ArrayList<GetListWithQueryResponse>();
        for(Presentation presentation : presentations) {
            GetListWithQueryResponse value = new GetListWithQueryResponse(presentation.getId(), presentation.getTitle(), tm.japaneseDateConverter(presentation.getDate()));
            res.add(value);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllTerm() {
        List<String> eq = new ArrayList<String>();
        List<Presentation> presentations = this.presentationRepo.getWithQuery(eq);
        Map<String, boolean[]> mp = new HashMap<>();
        for(Presentation presentation : presentations) {
            mp.put(presentation.getTerm(), null);
        }
        List<String> terms = new ArrayList<String>();
        for(String key : mp.keySet()) {
            terms.add(key);
        }
        return new ResponseEntity<>(terms, HttpStatus.OK);
    }
}

class GetListWithQueryResponse {
    public int id;
    public String title;
    public String date;

    public GetListWithQueryResponse(int id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }
}