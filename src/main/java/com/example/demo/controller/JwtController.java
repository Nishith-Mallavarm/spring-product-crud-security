package com.example.demo.controller;


import com.example.demo.util.JwtUtil;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class JwtController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/generate-token")
    public String generateToken(Principal principal){
        return jwtUtil.generateToken(principal.getName());
    }
}
