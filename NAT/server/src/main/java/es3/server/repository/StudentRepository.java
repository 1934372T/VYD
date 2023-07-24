package es3.server.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es3.server.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
   List<Student> getByStudentId(String studentId); 
}
