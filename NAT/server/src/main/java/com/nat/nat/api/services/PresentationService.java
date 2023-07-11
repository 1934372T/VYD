package com.nat.nat.api.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nat.nat.api.repository.interfaces.PaperRepositoryInterface;
import com.nat.nat.api.repository.interfaces.PresentationRepositoryInterface;
import com.nat.nat.api.repository.interfaces.SlideRepositoryInterface;
import com.nat.nat.api.repository.interfaces.StudentRepositoryInterface;
import com.nat.nat.api.services.interfaces.PresentationServiceInterface;
import com.nat.nat.entity.Paper;
import com.nat.nat.entity.Presentation;
import com.nat.nat.entity.Slide;
import com.nat.nat.entity.Student;
import com.nat.nat.lib.auth.TokenManager;
import com.nat.nat.lib.utils.StringOperator;
import com.nat.nat.lib.utils.TimeOperator;
import com.nat.nat.rules.Token;

@Service
public class PresentationService implements PresentationServiceInterface {
    private final PaperRepositoryInterface          paperRepo;
    private final SlideRepositoryInterface          slideRepo;
    private final PresentationRepositoryInterface   presentationRepo;
    private final StudentRepositoryInterface        studentRepo;

    public PresentationService(PaperRepositoryInterface paperRepo, SlideRepositoryInterface slideRepo, PresentationRepositoryInterface presentationRepo, StudentRepositoryInterface studentRepo) {
        this.paperRepo          = paperRepo;
        this.slideRepo          = slideRepo;
        this.presentationRepo   = presentationRepo;
        this.studentRepo        = studentRepo;
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

            List<String> query = new ArrayList<String>(Arrays.asList("studentId="+studentId));
            List<Student> students = this.studentRepo.getWithQuery(query);
            
            if(students.size() != 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Presentation newPresentation = new Presentation(students.get(0).getId(), title, so.convertIsoToLocalDateTime(date), note);
            newPresentation.setPaperId(createdPaper.getId());
            newPresentation.setSlideId(createdSlide.getId());
            String term = to.extractTermFromDate(so.convertIsoToLocalDateTime(date));
            newPresentation.setTerm(term);
            newPresentation.setDegree(students.get(0).getGrade());
            Presentation createdPresentation = this.presentationRepo.create(newPresentation);
            return new ResponseEntity<>(createdPresentation, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Presentation presentation = this.presentationRepo.getById((long) id);
        return new ResponseEntity<>(presentation, HttpStatus.OK);
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

    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }
}