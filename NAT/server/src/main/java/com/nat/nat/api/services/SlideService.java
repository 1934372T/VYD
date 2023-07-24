package com.nat.nat.api.services;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nat.nat.api.repository.interfaces.SlideRepositoryInterface;
import com.nat.nat.api.services.interfaces.SlideServiceInterface;
import com.nat.nat.entity.Slide;

@Service
public class SlideService implements SlideServiceInterface {
    private final SlideRepositoryInterface slideRepo;

    public SlideService(SlideRepositoryInterface slideRepo) {
        this.slideRepo = slideRepo;
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Slide slide = this.slideRepo.getById((long) id);
        byte[] encodedPdf = Base64.encodeBase64(slide.getBody());
        return new ResponseEntity<>(encodedPdf, HttpStatus.OK);
    }
}
