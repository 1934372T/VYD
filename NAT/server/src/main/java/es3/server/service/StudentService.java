package es3.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es3.server.entity.Student;
import es3.server.repository.StudentRepository;
import es3.server.rules.Grade;
import lombok.Data;

public interface StudentService {
    ResponseEntity<?> getList(Grade grade);
}

@Service
class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public ResponseEntity<?> getList(Grade grade) {
        Iterable<Student> students = this.studentRepo.findAll();
        List<GetStudentListWithQueryResponse> res = new ArrayList<GetStudentListWithQueryResponse>();
        for(Student s : students) {
            GetStudentListWithQueryResponse value = new GetStudentListWithQueryResponse(s.getId(), s.getFullName(), s.getStudentId(), s.getGrade());
            if(value.getGrade().equals(grade)) {
                res.add(value);
            }
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

@Data
class GetStudentListWithQueryResponse {
    public Long   id;
    public String name;
    public String student_id;
    public Grade  grade;

    public GetStudentListWithQueryResponse(Long id, String name, String student_id, Grade grade) {
        this.id         = id;
        this.name       = name;
        this.student_id = student_id;
        this.grade      = grade;
    }
}