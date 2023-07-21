package es3.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es3.server.entity.Paper;
import es3.server.entity.Presentation;
import es3.server.entity.Slide;
import es3.server.entity.Student;
import es3.server.lib.auth.TokenManager;
import es3.server.lib.utils.StringOperator;
import es3.server.lib.utils.TimeOperator;
import es3.server.repository.PaperRepository;
import es3.server.repository.PresentationRepository;
import es3.server.repository.SlideRepository;
import es3.server.repository.StudentRepository;
import es3.server.rules.Degree;
import es3.server.rules.Token;

public interface PresentationService {
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> getAllTerms();
    
}

@Service
class PresentationServiceImpl implements PresentationService {
    private final PaperRepository          paperRepo;
    private final SlideRepository          slideRepo;
    private final PresentationRepository   presentationRepo;
    private final StudentRepository        studentRepo;
    
    public PresentationServiceImpl(PaperRepository paperRepo, SlideRepository slideRepo, PresentationRepository presentationRepo, StudentRepository studentRepo) {
        this.paperRepo          = paperRepo;
        this.slideRepo          = slideRepo;
        this.presentationRepo   = presentationRepo;
        this.studentRepo        = studentRepo;
    }

    /**
     * 発表情報の登録
     * @param headers
     * @param paper
     * @param slide
     * @param title
     * @param date
     * @param note
     * @return
     */
    public ResponseEntity<?> register(List<String> headers, MultipartFile paper, MultipartFile slide, String title, String date, String note) {
        StringOperator so = new StringOperator();
        TimeOperator   to = new TimeOperator();
        TokenManager   tm = new TokenManager("example");
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

            Paper createdPaper = this.paperRepo.save(newPaper);
            Slide createdSlide = this.slideRepo.save(newSlide);

            List<Student> students = this.studentRepo.getByStudentId(studentId);
            
            if(students.size() != 1 || students.get(0) == null) {
                return new ResponseEntity<>("Student not found", HttpStatus.UNAUTHORIZED);
            }

            Presentation newPresentation = new Presentation(students.get(0).getId(), title, so.convertIsoToLocalDateTime(date), note);
            newPresentation.setPaperId(createdPaper.getId());
            newPresentation.setSlideId(createdSlide.getId());

            String term = to.extractTermFromDate(so.convertIsoToLocalDateTime(date));
            newPresentation.setTerm(term);

            newPresentation.setDegree(Degree.getDegree(students.get(0).getGrade()));
            Presentation createdPresentation = this.presentationRepo.save(newPresentation);

            return new ResponseEntity<>(createdPresentation, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 発表情報の取得
     * @param id
     * @return
     */
    public ResponseEntity<?> getById(Long id) {
        try {
            Optional<Presentation> presentation = this.presentationRepo.findById(id);
            return new ResponseEntity<>(presentation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getAllTerms() {
        List<String> terms = this.presentationRepo.getAllTerms();
        return new ResponseEntity<>(terms, HttpStatus.OK);
    }
}