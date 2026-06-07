package com.example.demo.repository;

import com.example.demo.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
