package com.example.demo.service;

import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.entity2.Products;
import com.example.demo.repository2.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Products addProduct(Products products) {
       return productRepository.save(products);
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public String deleteProduct(Long id) {
         productRepository.deleteById(id);
         return "Product Deleted";
    }

    public Products getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public String deleteAllProduct() {
        productRepository.deleteAll();
        return "All Products Deleted";
    }
}
