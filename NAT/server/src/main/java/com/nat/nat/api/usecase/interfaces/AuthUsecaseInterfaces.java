package com.nat.nat.api.usecase.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nat.nat.entity.Grade;

public interface AuthUsecaseInterfaces {
    public boolean isValidTokenUsecase(List<String> authHeaders);
    public ResponseEntity<?> signIn(String studentId, String password);
    public void signUp(String studentId, String password, String firstName, String lastName, Grade grade);
}