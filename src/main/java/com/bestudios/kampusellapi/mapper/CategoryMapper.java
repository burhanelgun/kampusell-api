package com.bestudios.kampusellapi.mapper;

import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.dto.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryDTO entityToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }


}
