package com.nat.nat.api.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nat.nat.api.repository.interfaces.StudentRepositoryInterfaces;
import com.nat.nat.api.usecase.interfaces.AuthUsecaseInterfaces;
import com.nat.nat.entity.Grade;
import com.nat.nat.entity.Student;

@Service
public class AuthUsecase implements AuthUsecaseInterfaces {

    private final StudentRepositoryInterfaces studentRepo;

    public AuthUsecase(StudentRepositoryInterfaces studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public boolean isValidTokenUsecase(List<String> authHeaders) {
        if (authHeaders != null && authHeaders.size() > 0) {
            String bearerToken = authHeaders.get(0);

            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);  // Extract the token from the string.

                // Your logic with the token goes here...

                System.out.println(token);

                return true;
            } 
        } 
        return false;
    }

    @Override
    public void signUp(String studentId, String password, String firstName, String lastName, Grade grade) {
        Student newStudent = new Student(firstName, lastName, grade, studentId, password);
        System.out.println(newStudent);
        studentRepo.create(newStudent);
    }

}
