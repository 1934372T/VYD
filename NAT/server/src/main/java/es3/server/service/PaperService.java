package es3.server.service;

import java.util.NoSuchElementException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es3.server.entity.Paper;
import es3.server.repository.PaperRepository;

public interface PaperService {
    ResponseEntity<?> getById(Long id);
}

@Service
class PaperServiceImpl implements PaperService {
    private final PaperRepository paperRepo;

    @Autowired
    public PaperServiceImpl(PaperRepository paperRepo) {
        this.paperRepo = paperRepo;
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        try {
            Paper paper = this.paperRepo.findById(id).orElseThrow();
            byte[] encodedPdf = Base64.encodeBase64(paper.getBody());
            return new ResponseEntity<>(encodedPdf, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}