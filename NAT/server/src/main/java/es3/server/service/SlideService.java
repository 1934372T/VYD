package es3.server.service;

import java.util.NoSuchElementException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es3.server.entity.Slide;
import es3.server.repository.SlideRepository;

public interface SlideService {
    ResponseEntity<?> getById(Long id);
}

@Service
class SlideServiceImpl implements SlideService {
    private final SlideRepository slideRepo;

    @Autowired
    public SlideServiceImpl(SlideRepository slideRepo) {
        this.slideRepo = slideRepo;
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        try {
            Slide slide = this.slideRepo.findById(id).orElseThrow();
            byte[] encodedPdf = Base64.encodeBase64(slide.getBody());
            return new ResponseEntity<>(encodedPdf, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}