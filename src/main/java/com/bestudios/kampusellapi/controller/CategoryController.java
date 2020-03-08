package com.bestudios.kampusellapi.controller;

import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

}
