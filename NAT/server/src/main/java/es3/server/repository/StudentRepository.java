package es3.server.repository;

import es3.server.entity.Student;

public interface StudentRepository extends CommonRepository<Student> {}

class studentRepository extends commonRepository<Student> implements StudentRepository {
    public studentRepository() {
        super(Student.class);
    }
}
