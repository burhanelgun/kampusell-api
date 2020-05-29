package com.bestudios.kampusellapi.service;

import com.bestudios.kampusellapi.dto.CategoryDTO;
import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.mapper.CategoryMapper;
import com.bestudios.kampusellapi.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    public CategoryDTO save(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return categoryMapper.entityToDTO(category);

    }
}
