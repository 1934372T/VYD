package es3.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es3.server.repository.StudentRepository;

public interface AuthService {}

@Service
class authService implements AuthService {

    @Autowired
    private final StudentRepository studentRepo;

    public authService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }
}