package es3.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es3.server.entity.Presentation;
import es3.server.entity.Slide;
import es3.server.repository.PaperRepository;
import es3.server.repository.PresentationRepository;
import es3.server.repository.SlideRepository;
import es3.server.repository.StudentRepository;

@Service
public interface PresentationService {
    
}

class PresentationServiceImpl implements PresentationService {
    private final PaperRepository          paperRepo;
    private final SlideRepository          slideRepo;
    private final PresentationRepository   presentationRepo;
    private final StudentRepository        studentRepo;
    
    @Autowired
    private Slide slideEntity;

    public PresentationServiceImpl(PaperRepository paperRepo, SlideRepository slideRepo, PresentationRepository presentationRepo, StudentRepository studentRepo) {
        this.paperRepo          = paperRepo;
        this.slideRepo          = slideRepo;
        this.presentationRepo   = presentationRepo;
        this.studentRepo        = studentRepo;
    }

    /**
     * 発表情報の登録
     * @param p 発表情報エンティティ
     * @return
     */
    public Presentation register(Presentation p) {
        slideEntity.setBody(null);
        studentRepo.existsById(p.getStudent().getId());
        paperRepo.save(p.getPaper());
        slideRepo.save(p.getSlide());
        return presentationRepo.save(p); 
    }
}