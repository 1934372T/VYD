package com.nat.nat.api.services.interfaces;

import org.springframework.http.ResponseEntity;

public interface SlideServiceInterface {
    ResponseEntity<?> getById(int id);
}
