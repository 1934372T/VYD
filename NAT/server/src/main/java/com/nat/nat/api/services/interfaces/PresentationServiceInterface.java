package com.nat.nat.api.services.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PresentationServiceInterface {
    ResponseEntity<?> create(List<String> headers, MultipartFile paper, MultipartFile slide, String title, String date, String note);
    ResponseEntity<?> getById(int id);
    ResponseEntity<?> getListWithQuery(List<String> queries);
    ResponseEntity<?> getAllTerm();
}
