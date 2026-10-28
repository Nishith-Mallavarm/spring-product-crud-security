package com.example.demo.controller;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MackBook {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @PreAuthorize("permitAll()")
    @GetMapping("/lap")
    public String getMackBookInfo() {
        System.out.println(entityManagerFactory.getProperties());
        return "This is a MackBook Air M4 controller.";
    }
}
