package com.example.demo.service;

import com.example.demo.entity.Laptop;
import com.example.demo.entity.Student;
import com.example.demo.repository.LaptopRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    StudentRepository studentRepository;

    LaptopRepository laptopRepository;

    public String addStudent(Student student) {
        Laptop laptop = student.getLaptop();
        System.out.println(laptop);
        laptop.setStudent(null);
        laptop=laptopRepository.save(laptop);
        student.setLaptop(laptop);
        studentRepository.save(student);
        return "success";
    }

    public List<Student> getAllStudents() {
        List<Student> students= studentRepository.findAll();
        return students;
    }
}
