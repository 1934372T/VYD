package es3.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es3.server.repository.SlideRepository;

public interface SlideService {
    
}

@Service
class SlideServiceImpl implements SlideService {
    private final SlideRepository slideRepo;

    @Autowired
    public SlideServiceImpl(SlideRepository slideRepo) {
        this.slideRepo = slideRepo;
    }
}