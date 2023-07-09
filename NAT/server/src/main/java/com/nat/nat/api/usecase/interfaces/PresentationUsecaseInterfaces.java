package com.nat.nat.api.usecase.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PresentationUsecaseInterfaces {
    public ResponseEntity<?> create(List<String> headers, MultipartFile paper, MultipartFile slide, String title, String date, String note);
    public ResponseEntity<?> getListWithQuery(List<String> queries);
    public ResponseEntity<?> getAllTerm();
}
