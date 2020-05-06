package com.bestudios.kampusellapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private double price;

    private StudentDTO student;

    private CategoryDTO category;

    private List<String> imagePaths;
}
