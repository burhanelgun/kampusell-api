package com.bestudios.kampusellapi.controller;

import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.entity.Product;
import com.bestudios.kampusellapi.entity.Student;
import com.bestudios.kampusellapi.service.ProductService;
import dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/hellos")
    public String test() {
        return "MERHABALAR";
    }


    @GetMapping
    public List<ProductDTO> getAllProjects() {
        return productService.getAllProjects();
    }

    @PostMapping
    public ResponseEntity createProject(@RequestBody ProductDTO productDTO) {

        return ResponseEntity.ok(productService.save(productDTO));
    }





}