package es3.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es3.server.repository.PaperRepository;

public interface PaperService {
    
}

@Service
class PaperServiceImpl implements PaperService {
    private final PaperRepository paperRepo;

    @Autowired
    public PaperServiceImpl(PaperRepository paperRepo) {
        this.paperRepo = paperRepo;
    }
}