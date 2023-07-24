package com.nat.nat.api.services.interfaces;

import org.springframework.http.ResponseEntity;

public interface PaperServiceInterface {
    ResponseEntity<?> getById(int id);
}
