package com.example.demo.service;

import com.example.demo.entity2.Users;
import com.example.demo.repository2.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsersService {

    UsersRepository usersRepository;

    public void addUsers(Users users) {
        String password = users.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        users.setPassword(passwordEncoder.encode(password));
        usersRepository.save(users);
    }
}
