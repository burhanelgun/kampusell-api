package com.bestudios.kampusellapi.service;

import com.bestudios.kampusellapi.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
}
