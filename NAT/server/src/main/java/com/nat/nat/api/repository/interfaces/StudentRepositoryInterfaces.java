package com.nat.nat.api.repository.interfaces;

import com.nat.nat.entity.Student;

public interface StudentRepositoryInterfaces {
    public void create(Student student);
    public Student getById(Long id);
    public Student getByStudentId(String studentId);
}
