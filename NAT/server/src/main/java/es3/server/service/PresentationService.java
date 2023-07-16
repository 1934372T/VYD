package es3.server.service;

import org.springframework.stereotype.Service;

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

    public PresentationServiceImpl(PaperRepository paperRepo, SlideRepository slideRepo, PresentationRepository presentationRepo, StudentRepository studentRepo) {
        this.paperRepo          = paperRepo;
        this.slideRepo          = slideRepo;
        this.presentationRepo   = presentationRepo;
        this.studentRepo        = studentRepo;
    }
}