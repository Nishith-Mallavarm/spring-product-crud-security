package com.example.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    final String signature="wetbkbvbvkhvhvnvmvlvjoijvihthtyutttbbt";

    Key signingKey= Keys.hmacShaKeyFor(signature.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username){
               return Jwts.builder()
                       .setHeaderParam("typ","JWT")
                        .setSubject(username)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis()+60*60*1000))
                        .signWith(signingKey, SignatureAlgorithm.HS256)
                        .compact();
    }

    public String parseToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

}
