package com.bestudios.kampusellapi.mapper;

import com.bestudios.kampusellapi.entity.Product;
import dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {
    public ProductDTO entityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        //need to add the other product details

        return productDTO;
    }

    public List<ProductDTO> entityToDtoList(List<Product> products) {
        List<ProductDTO> projectsDto = new ArrayList<ProductDTO>();
        for (Product product : products) {
            projectsDto.add(entityToDto(product));
        }
        return projectsDto;
    }

}
