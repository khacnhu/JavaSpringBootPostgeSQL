package com.example.JavaPostgresqlResearch.service;

import com.example.JavaPostgresqlResearch.model.Student;
import com.example.JavaPostgresqlResearch.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public void deleteStudent(Integer studentId) throws IllegalAccessException {
        boolean existStudent = studentRepository.existsById(studentId);

        if(!existStudent) {
            throw new IllegalAccessException("students id is not existed " + studentId);
        }

        studentRepository.deleteById(studentId);

    }


    @Transactional
    public void updateStudent(Integer studentId, String name, String email) throws IllegalAccessException {
        Student existStudent =studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("student with id is not existed " + studentId)
        );


        if(name != null && name.length() > 0 && !Objects.equals(existStudent.getName(), name) ) {
            existStudent.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(existStudent.getEmail(), email) ) {
            Optional<Student> optionalStudent   = studentRepository.findStudentByEmail(email);
            if(optionalStudent.isPresent()) {
                throw new IllegalAccessException("email is existed");
            }

            existStudent.setEmail(email);
        }

        studentRepository.save(existStudent);
    }



}
