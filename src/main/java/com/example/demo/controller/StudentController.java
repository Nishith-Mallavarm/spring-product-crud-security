package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private StudentService studentService;

    @PostMapping
    public String addStudent(@RequestBody Student student){
       return  studentService.addStudent(student);
    }
    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
}
