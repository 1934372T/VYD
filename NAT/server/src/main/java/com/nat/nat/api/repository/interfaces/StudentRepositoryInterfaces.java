package com.nat.nat.api.repository.interfaces;

import com.nat.nat.domain.Student;

public interface StudentRepositoryInterfaces {
    public void create(Student student);
    public Student getById(Long id);
}
