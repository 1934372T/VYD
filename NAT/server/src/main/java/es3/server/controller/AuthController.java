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

    private static final String IS_VALID_TOKEN  = "/is-valid-token";
    private static final String SIGN_IN         = "/signin";
    private static final String SIGN_UP         = "/signup";

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping(IS_VALID_TOKEN)
    public ResponseEntity<?> isValidToken(@RequestHeader HttpHeaders headers) {
        List<String> authHeaders = headers.get("Authorization");
        return this.service.isValidToken(authHeaders);
    }

    @PostMapping(SIGN_IN)
    public ResponseEntity<?> signIn(@RequestBody SignInForm form) {
        String studentId = form.getStudentId();
        String password = form.getPassword();
        return this.service.signIn(studentId, password);
    }

    @PostMapping(SIGN_UP)
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

@Getter
@Setter
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
