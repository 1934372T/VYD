package es3.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es3.server.repository.PaperRepository;
import es3.server.repository.PresentationRepository;
import es3.server.repository.SlideRepository;
import es3.server.repository.StudentRepository;

public interface PresentationService {}

@Service
class presentationService implements PresentationService {

    @Autowired
    private final PaperRepository        paperRepo;
    @Autowired
    private final SlideRepository        slideRepo;
    @Autowired
    private final PresentationRepository presentationRepo;
    @Autowired
    private final StudentRepository      studentRepo;

    public presentationService(PaperRepository paperRepo, SlideRepository slideRepo, PresentationRepository presentationRepo, StudentRepository studentRepo) {
        this.paperRepo        = paperRepo;
        this.slideRepo        = slideRepo;
        this.presentationRepo = presentationRepo;
        this.studentRepo      = studentRepo;
    }
}