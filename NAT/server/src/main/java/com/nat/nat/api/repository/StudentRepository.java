package com.nat.nat.api.repository;

import org.springframework.stereotype.Repository;

import com.nat.nat.api.repository.interfaces.StudentRepositoryInterfaces;
import com.nat.nat.entity.Student;

import jakarta.persistence.TypedQuery;

@Repository
public class StudentRepository extends CommonRepository<Student> implements StudentRepositoryInterfaces {
    
    public StudentRepository() {
        super(Student.class);
    }

    @Override
    public Student getByStudentId(String studentId) {
        String queryString = "SELECT s FROM Student s WHERE s.studentId = :studentId";
        TypedQuery<Student> query = entityManager.createQuery(queryString, Student.class);
        query.setParameter("studentId", studentId);
        return query.getSingleResult();
    }
}
