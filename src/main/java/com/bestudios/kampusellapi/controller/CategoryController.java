package com.bestudios.kampusellapi.controller;

import com.bestudios.kampusellapi.dto.CategoryDTO;
import com.bestudios.kampusellapi.dto.ProductDTO;
import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.save(categoryDTO));
    }


}
