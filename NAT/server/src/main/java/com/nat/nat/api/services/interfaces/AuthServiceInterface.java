package com.nat.nat.api.services.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nat.nat.rules.Grade;

public interface AuthServiceInterface {
    ResponseEntity<?> isValidToken(List<String> authHeaders);
    ResponseEntity<?> signIn(String studentId, String password);
    ResponseEntity<?> signUp(String studentId, String password, String firstName, String lastName, Grade grade);
}