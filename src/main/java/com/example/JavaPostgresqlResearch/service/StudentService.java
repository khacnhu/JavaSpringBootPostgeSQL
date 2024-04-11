package com.example.JavaPostgresqlResearch.service;

import com.example.JavaPostgresqlResearch.model.Student;
import com.example.JavaPostgresqlResearch.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    public ResponseEntity<Object> createStudent(Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok().build();
    }




}
