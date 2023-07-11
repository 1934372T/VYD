package com.nat.nat.api.services.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nat.nat.rules.Grade;

public interface AuthServiceInterface {
    public ResponseEntity<?> isValidToken(List<String> authHeaders);
    public ResponseEntity<?> signIn(String studentId, String password);
    public ResponseEntity<?> signUp(String studentId, String password, String firstName, String lastName, Grade grade);
}