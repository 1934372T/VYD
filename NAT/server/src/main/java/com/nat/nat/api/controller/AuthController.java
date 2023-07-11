package com.nat.nat.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nat.nat.api.services.interfaces.AuthServiceInterface;
import com.nat.nat.rules.Grade;
import com.nat.nat.rules.Consts;

@RestController
@RequestMapping(Consts.API_PREFIX+"/auth")
public class AuthController {

    private final AuthServiceInterface service;

    @Autowired
    public AuthController(AuthServiceInterface service) {
        this.service = service;
    }

    @PostMapping("/is-valid-token")
    public ResponseEntity<?> isValidToken(@RequestHeader HttpHeaders headers) {
        List<String> authHeaders = headers.get("Authorization");
        return this.service.isValidToken(authHeaders);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInForm form) {
        String studentId = form.getStudentId();
        String password = form.getPassword();
        return this.service.signIn(studentId, password);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpForm form) {
        String studentId = form.getStudentId();
        String password = form.getPassword();
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        Grade grade = form.getGrade();
        return this.service.signUp(studentId, password, firstName, lastName, grade);
    }
}

class SignInForm {
    @JsonProperty("student_id")
    private String studentId;

    @JsonProperty("password")
    private String password;

    public String getStudentId() {
        return this.studentId;
    }

    public String getPassword() {
        return this.password;
    }
}

class SignUpForm {
    @JsonProperty("student_id")
    private String studentId; // 学籍番号

    @JsonProperty("password")
    private String password; // パスワード（ハッシュ化前）
    
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("grade")
    private Grade grade;
    
    public String getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Grade getGrade() {
        return this.grade;
    }
}
