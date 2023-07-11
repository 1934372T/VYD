package com.nat.nat.api.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nat.nat.api.repository.interfaces.StudentRepositoryInterface;
import com.nat.nat.api.services.interfaces.AuthServiceInterface;
import com.nat.nat.entity.Student;
import com.nat.nat.lib.auth.TokenManager;
import com.nat.nat.lib.utils.StringOperator;
import com.nat.nat.rules.Grade;
import com.nat.nat.rules.Permission;

@Service
public class AuthService implements AuthServiceInterface {

    private final StudentRepositoryInterface studentRepo;

    public AuthService(StudentRepositoryInterface studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public ResponseEntity<?> isValidToken(List<String> authHeaders) {
        TokenManager tm = new TokenManager("example");
        String token = tm.getTokenFromHeaders(authHeaders);
        tm.setToken(token);
        return new ResponseEntity<>(tm.isValidToken() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> signIn(String studentId, String password) {
        StringOperator so = new StringOperator();
        TokenManager tm = new TokenManager("example", studentId, Permission.valueOf("STUDENT"));
        
        List<String> query = new ArrayList<String>(Arrays.asList("studentId="+studentId));
        List<Student> students = this.studentRepo.getWithQuery(query);
        
        if(students.size() != 1 || students.get(0) == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.UNAUTHORIZED);
        }

        if(!so.comparePasswords(password, students.get(0).getPassword())) {
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
