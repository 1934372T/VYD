package com.nat.nat.api.services;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nat.nat.api.repository.interfaces.PaperRepositoryInterface;
import com.nat.nat.api.services.interfaces.PaperServiceInterface;
import com.nat.nat.entity.Paper;

@Service
public class PaperService implements PaperServiceInterface {
    private final PaperRepositoryInterface paperRepo;

    public PaperService(PaperRepositoryInterface paperRepo) {
        this.paperRepo = paperRepo;
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Paper paper = this.paperRepo.getById((long) id);
        byte[] encodedPdf = Base64.encodeBase64(paper.getBody());
        return new ResponseEntity<>(encodedPdf, HttpStatus.OK);
    }
}
