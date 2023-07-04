package com.nat.nat.domain;

import java.util.Date;

public class Token {
    public String subject;
    public Date issuedAt;
    public Date notBefore;
    public Date expiredAt;
    public String permission;
    public String userId;

    public Token(String subject, Date issuedAt, Date notBefore, Date expiredAt, String permission, String userId) {
        this.subject = subject;
        this.issuedAt = issuedAt;
        this.notBefore = notBefore;
        this.expiredAt = expiredAt;
        this.permission = permission;
        this.userId = userId;
    }
}
