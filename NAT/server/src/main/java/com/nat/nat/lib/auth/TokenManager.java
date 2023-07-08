package com.nat.nat.lib.auth;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nat.nat.entity.Permission;
import com.nat.nat.entity.Token;

public class TokenManager {
    private String secretKey;
    private String userId;
    private Permission permission;
    private String token;
    private static final Long EXPIRATION_TIME = 1000L * 60L * 10L;

    public TokenManager(String secretKey) {
        this.secretKey = secretKey;
    }

    public TokenManager(String secretKey, String token) {
        this.secretKey = secretKey;
        this.token = token;
    }

    public TokenManager(String secretKey, String userId, Permission permission) {
        this.secretKey = secretKey;
        this.userId = userId;
        this.permission = permission;
    }

    public String getTokenFromHeaders(List<String> authHeaders) {
        String tk = "";
        if (authHeaders != null && authHeaders.size() > 0) {
            String bearerToken = authHeaders.get(0);

            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                tk = bearerToken.substring(7);  // Extract the token from the string.
            } 
        } 
        return tk;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String generateToken() {
        Algorithm algo = Algorithm.HMAC256(secretKey);
        Date issuedAt = new Date();
        Date notBefore = new Date(issuedAt.getTime());
        Date expiredAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
        UUID uuid = UUID.randomUUID();
        this.token = JWT.create()
            .withSubject(uuid.toString())
            .withIssuedAt(issuedAt)
            .withNotBefore(notBefore)
            .withExpiresAt(expiredAt)
            .withClaim("PERMISSION", permission.toString())
            .withClaim("USER_ID", userId)
            .sign(algo);

        return this.token;
    }

    public Token decodeToken() {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT jwt = verifier.verify(token);

        // registered claims
        String subject = jwt.getSubject();
        Date issuedAt = jwt.getIssuedAt();
        Date notBefore = jwt.getNotBefore();
        Date expiresAt = jwt.getExpiresAt();

        // private claims
        String permission = jwt.getClaim("PERMISSION").asString();
        String userId = jwt.getClaim("USER_ID").asString();

        Token decodedToken = new Token(subject, issuedAt, notBefore, expiresAt, permission, userId);
        return decodedToken;
    }

    public boolean isValidToken() {
        if(this.token.isEmpty()) {
            return false;
        }
        Token decodedToken = decodeToken();
        Date checkedAt = new Date();
        if(checkedAt.after(decodedToken.expiredAt)) {
            return false;
        } else {
            return true;
        }
    }
}
