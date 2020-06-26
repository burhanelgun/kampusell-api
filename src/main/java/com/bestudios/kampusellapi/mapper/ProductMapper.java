package com.bestudios.kampusellapi.mapper;

import com.bestudios.kampusellapi.dto.ProductDTO;
import com.bestudios.kampusellapi.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    StudentMapper studentMapper;

    public ProductDTO entityToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(categoryMapper.entityToDTO(product.getCategory()));
        productDTO.setStudent(studentMapper.entityToDTO(product.getStudent()));
        productDTO.setImagePaths(product.getImagePaths());
        productDTO.setTexts(product.getTexts());
        productDTO.setLabel1(product.getLabel1());
        productDTO.setLabel2(product.getLabel2());


        return productDTO;
    }

    public List<ProductDTO> entityToDTOList(List<Product> products) {
        List<ProductDTO> productsDTO = new ArrayList<ProductDTO>();
        for (Product product : products) {
            productsDTO.add(entityToDTO(product));
        }
        return productsDTO;
    }

}
