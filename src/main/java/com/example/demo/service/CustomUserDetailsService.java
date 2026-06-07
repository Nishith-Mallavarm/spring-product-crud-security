package com.example.demo.service;


import com.example.demo.entity2.Users;
import com.example.demo.repository2.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    UsersRepository  usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u=usersRepository.findByUsername(username);

        if(u==null){
            return new Users();
        }
        return u;
    }

}
