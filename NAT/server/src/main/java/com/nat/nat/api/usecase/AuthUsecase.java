package com.nat.nat.api.usecase;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nat.nat.api.repository.interfaces.StudentRepositoryInterfaces;
import com.nat.nat.api.usecase.interfaces.AuthUsecaseInterfaces;
import com.nat.nat.entity.Student;
import com.nat.nat.lib.auth.TokenManager;
import com.nat.nat.lib.utils.StringOperator;
import com.nat.nat.rules.Grade;
import com.nat.nat.rules.Permission;

@Service
public class AuthUsecase implements AuthUsecaseInterfaces {

    private final StudentRepositoryInterfaces studentRepo;

    public AuthUsecase(StudentRepositoryInterfaces studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public ResponseEntity<?> isValidTokenUsecase(List<String> authHeaders) {
        if (authHeaders != null && authHeaders.size() > 0) {
            String bearerToken = authHeaders.get(0);

            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);  // Extract the token from the string.

                TokenManager tm = new TokenManager("example", token);

                return new ResponseEntity<>(tm.isValidToken() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
            } 
        } 
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> signIn(String studentId, String password) {
        StringOperator so = new StringOperator();
        TokenManager tm = new TokenManager("example", studentId, Permission.valueOf("STUDENT"));
        Student student = this.studentRepo.getByStudentId(studentId);
        if(student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.UNAUTHORIZED);
        }

        if(!so.comparePasswords(password, student.getPassword())) {
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }

        String accessToken = tm.generateToken();

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> signUp(String studentId, String password, String firstName, String lastName, Grade grade) {
        StringOperator so = new StringOperator();
        String hashedPassword = so.sha256Hash(password);
        Student newStudent = new Student(firstName, lastName, grade, studentId, hashedPassword);
        this.studentRepo.create(newStudent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
