package com.example.demo.controller;

import com.example.demo.entity2.Products;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public Products addProduct(@RequestBody Products products){
        return productService.addProduct(products);
    }

    @PreAuthorize("hasAuthority('ROLE_READ')")
    @GetMapping("/all")
    public List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
       return  productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/delete")
    public String deleteAllProducts(){
       return productService.deleteAllProduct();
    }
}
