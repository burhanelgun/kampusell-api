package com.bestudios.kampusellapi.controller;

import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.entity.Product;
import com.bestudios.kampusellapi.entity.Student;
import com.bestudios.kampusellapi.model.ProductFilter;
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
import java.util.Map;
import java.util.Optional;

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
    public List<ProductDTO> getAllProducts() {
        log.info("helloooooo1");
        return productService.getAllProducts();
    }

    @GetMapping("/categoryId={categoryId}")
    public List<ProductDTO> getProductsByCategoryId(@PathVariable("categoryId") Optional<String> categoryId) {
        log.info("helloooooo2");
        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/searchText={searchText}")
    public List<ProductDTO> getSearchTextProducts(@PathVariable("searchText") Optional<String> searchText) {
        return productService.getProductsBySearchText(searchText);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.save(productDTO));
    }


    @PostMapping("/s")
    public ResponseEntity createProduct2(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.save(productDTO));
    }

    @PostMapping("/filter")
    public List<ProductDTO> createProduct2(@RequestBody Optional<ProductFilter> productFilter) {
        return productService.getFilteredProducts(productFilter);
    }





}