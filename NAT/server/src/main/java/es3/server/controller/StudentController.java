package es3.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es3.server.rules.Consts;
import es3.server.rules.Grade;
import es3.server.service.StudentService;

@RestController
@RequestMapping(Consts.API_PREFIX+"/student")
public class StudentController {
    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    } 
    
    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam("grade") String g) {
        Grade grade = Grade.valueOf(g);
        return this.service.getList(grade);
    }
}
