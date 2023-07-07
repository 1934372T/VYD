package com.nat.nat.api.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nat.nat.api.usecase.interfaces.AuthUsecaseInterfaces;

@Service
public class AuthUsecase implements AuthUsecaseInterfaces {

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

}
