package com.nat.nat.api.usecase.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PresentationUsecaseInterfaces {
    public ResponseEntity<?> create(List<String> headers, MultipartFile paper, MultipartFile slide, String title, LocalDateTime date, String note);
}
