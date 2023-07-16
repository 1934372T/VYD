package es3.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import es3.server.rules.Consts;
import es3.server.rules.Grade;
import es3.server.service.PresentationService;
import lombok.Data;

@RestController
@RequestMapping(Consts.API_PREFIX+"/presentation")
public class PresentationController {
    private final PresentationService service;

    @Autowired
    public PresentationController(PresentationService service) {
        this.service = service;
    }
}

@Data
class SignInForm {
    @JsonProperty("student_id")
    private String studentId;

    @JsonProperty("password")
    private String password;
}

@Data
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
}