package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.students.Student;
import com.example.demo.students.StudentRepository;

@Controller
public class PageController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    public String showStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "showStudents";
    }

    @GetMapping("addStudent")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @PostMapping("addStudent")
    public String addStudentSubmit(@ModelAttribute Student student) {
        studentRepository.save(student);
        return null;
    }

    @GetMapping("updateStudent")
    public String updateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "updateStudent";
    }

    @PostMapping("updateStudent")
    public String updateStudentSubmit(@ModelAttribute Student student) {
        studentRepository.save(student);
        return null;
    }

    @GetMapping("deleteStudent")
    public String deleteStudentForm(Model model){
        model.addAttribute("student", new Student());
        return "deleteStudent";
    }

    @PostMapping("deleteStudent")
    public String deleteStudentSubmit(@ModelAttribute Student student) {
        studentRepository.deleteById(student.getId());
        return null;
    }

    @GetMapping("findStudent")
    public String findStudentForm(Model model){
        model.addAttribute("student", new Student());
        return "findStudent";
    }

    @PostMapping("findStudent")
    public String findStudentSubmit(@ModelAttribute Student student, Model model) {
        model.addAttribute("students",studentRepository.findByFirstName(student.getFirstName()));
        return "showStudents";
    }
}
