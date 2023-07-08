package com.nat.nat.api.usecase.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nat.nat.entity.Grade;

public interface AuthUsecaseInterfaces {
    public ResponseEntity<?> isValidTokenUsecase(List<String> authHeaders);
    public ResponseEntity<?> signIn(String studentId, String password);
    public ResponseEntity<?> signUp(String studentId, String password, String firstName, String lastName, Grade grade);
}