package com.example.demo.entity2;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;
}
