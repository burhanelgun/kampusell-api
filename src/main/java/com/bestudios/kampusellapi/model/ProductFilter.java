package com.bestudios.kampusellapi.model;

import com.bestudios.kampusellapi.entity.Category;
import lombok.Data;

@Data
public class ProductFilter {
    Category category;
    double minPrice;
    double maxPrice;
}
