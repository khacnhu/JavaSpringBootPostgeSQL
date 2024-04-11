package com.example.JavaPostgresqlResearch.controller;

import com.example.JavaPostgresqlResearch.model.Student;
import com.example.JavaPostgresqlResearch.repository.StudentRepository;
import com.example.JavaPostgresqlResearch.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Student>> getIndex(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<Object> registerStudent(@RequestBody Student student) throws IllegalAccessException {

        Optional<Student> optionalStudent   = studentRepository.findStudentByEmail(student.getEmail());
        if(optionalStudent.isPresent()) {
            throw new IllegalAccessException("email is existed");
        }

        return studentService.createStudent(student);
    }

}
