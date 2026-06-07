package com.example.demo.config;

import com.example.demo.filter.JwtFilter;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class FilterChain {



     @Autowired
     JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(auth->
                auth.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    /*
            There are 3 rules
            1. if we are using request matcher and hasAuthority then endpoint must match
            and user is authenticated before authorization
            2.if we are using request matcher and permit then user
            need not to be authenticated
            3.if we are using any request is authenticated then user
            must authenticated need not to be authorization
     */

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomUserDetailsService customUserDetailsService,BCryptPasswordEncoder encoder) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }


}
