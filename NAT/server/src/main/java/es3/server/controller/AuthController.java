package es3.server.controller;

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

import es3.server.rules.Consts;
import es3.server.rules.Grade;
import es3.server.service.AuthService;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping(Consts.API_PREFIX+"/auth")
public class AuthController {
    private final AuthService service;

    private static final String IS_VALID_TOKEN      = "/is-valid-token";
    private static final String SIGN_IN_FOR_STUDENT = "/student/signin";
    private static final String SIGN_UP_FOR_STUDENT = "/student/signup";
    private static final String SIGN_IN_FOR_ADMIN   = "/admin/signin";
    private static final String SIGN_UP_FOR_ADMIN   = "/admin/signup";

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping(IS_VALID_TOKEN)
    public ResponseEntity<?> isValidToken(@RequestHeader HttpHeaders headers) {
        List<String> authHeaders = headers.get("Authorization");
        return this.service.isValidToken(authHeaders);
    }

    @PostMapping(SIGN_IN_FOR_STUDENT)
    public ResponseEntity<?> signInForStudent(@RequestBody SignInFormForStudent form) {
        String studentId = form.getStudentId();
        String password = form.getPassword();
        return this.service.signInForStudent(studentId, password);
    }

    @PostMapping(SIGN_UP_FOR_STUDENT)
    public ResponseEntity<?> signUpForStudent(@RequestBody SignUpFormForStudent form) {
        String studentId = form.getStudentId();
        String password = form.getPassword();
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        Grade grade = form.getGrade();
        return this.service.signUpForStudent(studentId, password, firstName, lastName, grade);
    }

    @PostMapping(SIGN_IN_FOR_ADMIN)
    public ResponseEntity<?> signInForAdmin(@RequestBody SignInFormForAdmin form) {
        String email = form.getEmail();
        String password = form.getPassword();
        return this.service.signInForAdmin(email, password);
    }

    @PostMapping(SIGN_UP_FOR_ADMIN)
    public ResponseEntity<?> signUpForAdmin(@RequestBody SignUpFormForAdmin form) {
        String email = form.getEmail();
        String password = form.getPassword();
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        return this.service.signUpForAdmin(email, password, firstName, lastName);
    }

}

@Getter
@Setter
class SignInFormForStudent {
    @JsonProperty("student_id")
    private String studentId;

    @JsonProperty("password")
    private String password;

}

@Getter
@Setter
class SignInFormForAdmin {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}

@Getter
@Setter
class SignUpFormForStudent {
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

@Getter
@Setter
class SignUpFormForAdmin {
    @JsonProperty("email")
    private String email; // 学籍番号

    @JsonProperty("password")
    private String password; // パスワード（ハッシュ化前）
    
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

}