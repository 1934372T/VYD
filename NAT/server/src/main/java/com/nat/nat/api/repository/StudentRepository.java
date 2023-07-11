package com.nat.nat.api.repository;

import org.springframework.stereotype.Repository;

import com.nat.nat.api.repository.interfaces.StudentRepositoryInterface;
import com.nat.nat.entity.Student;

@Repository
public class StudentRepository extends CommonRepository<Student> implements StudentRepositoryInterface {

    public StudentRepository() {
        super(Student.class);
    }
    
}
