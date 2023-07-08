package com.nat.nat.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nat.nat.api.repository.interfaces.StudentRepositoryInterfaces;
import com.nat.nat.entity.Student;

import jakarta.persistence.EntityManager;

@Repository
public class StudentRepository implements StudentRepositoryInterfaces {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student getById(Long id) {
        return entityManager.find(Student.class, id);
    }
}
