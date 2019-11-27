package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.students.Student;
import com.example.demo.students.StudentRepository;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("student/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @GetMapping("students/{firstName}")
    public List<Student> getStudentsByFirstName(@PathVariable String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    @PostMapping("student")
    public void createStudent(@RequestBody Student request) {
        studentRepository.save(new Student(request.getFirstName(), request.getLastName()));
    }

    @PutMapping("student")
    public void updateStudent(@RequestBody Student request) {
        Optional<Student> student = studentRepository.findById(request.getId());
        if (student.isPresent()) {
            student.get().setFirstName(request.getFirstName());
            student.get().setLastName(request.getLastName());
            studentRepository.save(student.get());
        }
    }

    @DeleteMapping("student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}