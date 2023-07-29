package es3.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es3.server.entity.Admin;
import es3.server.entity.Student;
import es3.server.lib.auth.TokenManager;
import es3.server.lib.utils.StringOperator;
import es3.server.repository.AdminRepository;
import es3.server.repository.StudentRepository;
import es3.server.rules.Grade;
import es3.server.rules.Permission;

public interface AuthService {
    ResponseEntity<?> isValidToken(List<String> authHeaders);
    ResponseEntity<?> signInForStudent(String studentId, String password);
    ResponseEntity<?> signUpForStudent(String studentId, String password, String firstName, String lastName, Grade grade);
    ResponseEntity<?> signInForAdmin(String email, String password);
    ResponseEntity<?> signUpForAdmin(String email, String password, String firstName, String lastName);
}

@Service
class AuthServiceImpl implements AuthService {
    private final StudentRepository studentRepo;
    private final AdminRepository   adminRepository;

    @Autowired
    public AuthServiceImpl(StudentRepository studentRepo, AdminRepository adminRepository) {
        this.studentRepo     = studentRepo;
        this.adminRepository = adminRepository;
    }

    @Override
    public ResponseEntity<?> isValidToken(List<String> authHeaders) {
        TokenManager tm     = new TokenManager("example");
        String       token  = tm.getTokenFromHeaders(authHeaders);
        tm.setToken(token);
        return new ResponseEntity<>(tm.isValidToken() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> signInForStudent(String studentId, String password) {
        StringOperator  so       = new StringOperator();
        TokenManager    tm       = new TokenManager("example", studentId, Permission.valueOf("STUDENT"));
        List<Student> students = this.studentRepo.getByStudentId(studentId);
        
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
    public ResponseEntity<?> signUpForStudent(String studentId, String password, String firstName, String lastName, Grade grade) {
        StringOperator  so              = new StringOperator();
        String          hashedPassword  = so.sha256Hash(password);
        Student         newStudent      = new Student(firstName, lastName, grade, studentId, hashedPassword);
        this.studentRepo.save(newStudent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> signInForAdmin(String email, String password) {
        StringOperator so     = new StringOperator();
        TokenManager   tm     = new TokenManager("example", email, Permission.valueOf("ADMIN"));
        List<Admin>    admins = this.adminRepository.getByEmail(email);
        
        if(admins.size() != 1 || admins.get(0) == null) {
            return new ResponseEntity<>("Admin not found", HttpStatus.UNAUTHORIZED);
        }

        if(!so.comparePasswords(password, admins.get(0).getPassword())) {
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }

        String accessToken = tm.generateToken();

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> signUpForAdmin(String email, String password, String firstName, String lastName) {
        StringOperator  so              = new StringOperator();
        String          hashedPassword  = so.sha256Hash(password);
        Admin           newAdmin        = new Admin(firstName, lastName, email, hashedPassword);
        // this.studentRepo.save(newStudent);
        this.adminRepository.save(newAdmin);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}