package es3.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es3.server.repository.StudentRepository;

public interface AuthService {
    
}

@Service
class AuthServiceImpl implements AuthService {
    private final StudentRepository studentRepo;

    @Autowired
    public AuthServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }
}