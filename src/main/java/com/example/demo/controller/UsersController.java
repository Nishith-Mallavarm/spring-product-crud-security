package com.example.demo.controller;

import com.example.demo.entity2.Users;
import com.example.demo.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@AllArgsConstructor
@RestController
public class UsersController {

    UsersService usersService;

    @PostMapping
    public String addUsers(@RequestBody Users users){
        usersService.addUsers(users);
        return "success";
    }
}
