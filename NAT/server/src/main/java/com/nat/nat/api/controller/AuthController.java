package com.nat.nat.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nat.nat.api.usecase.interfaces.AuthUsecaseInterfaces;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthUsecaseInterfaces usecase;

    @Autowired
    public AuthController(AuthUsecaseInterfaces usecase) {
        this.usecase = usecase;
    }

    @PostMapping("/is-valid-token")
    public ResponseEntity<String> isValidTokenController(@RequestHeader HttpHeaders headers) {
        List<String> authHeaders = headers.get("Authorization");
        boolean isValidToken = this.usecase.isValidTokenUsecase(authHeaders);
        if(isValidToken){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/signin")
    public void signIn() {}

    @PostMapping("/signup")
    public void signUp() {}
}
