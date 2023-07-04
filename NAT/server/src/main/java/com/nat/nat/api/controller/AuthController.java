package com.nat.nat.api.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping("/is-valid-token")
    public void isValidToken(@RequestHeader HttpHeaders headers) {
        List<String> authHeaders = headers.get("Authorization");
    
        if (authHeaders != null && authHeaders.size() > 0) {
            String bearerToken = authHeaders.get(0);

            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);  // Extract the token from the string.

                // Your logic with the token goes here...

                System.out.println(token);
            } else {
                System.out.println("Bearer token is not present in the Authorization header.");
            }
        } else {
            System.out.println("Authorization header is missing.");
        }
    }

    @PostMapping("/login")
    public void login() {}
}
