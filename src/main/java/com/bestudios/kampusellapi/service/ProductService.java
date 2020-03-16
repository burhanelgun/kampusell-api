package com.bestudios.kampusellapi.service;

import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.entity.Product;
import com.bestudios.kampusellapi.entity.Student;
import com.bestudios.kampusellapi.mapper.ProductMapper;
import com.bestudios.kampusellapi.repository.CategoryRepository;
import com.bestudios.kampusellapi.repository.ProductRepository;
import com.bestudios.kampusellapi.repository.StudentRepository;
import dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    //silinmeli
    @Autowired
    CategoryRepository categoryRepository;

    //silinmeli
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProductMapper productMapper;

    public List<ProductDTO> getAllProjects() {
        log.info("Get all Projects");
        return productMapper.entityToDTOList(productRepository.findAll());
    }

    public ProductDTO save(ProductDTO productDTO) {
        //deneme olarak DTO olarak alıyorum ve onu kendim rastgele tamamlayıp product yapacağım.
        //normalde de ProductDTO olarak alacağım ama mapper kullanarak Product a çevireceğim.
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setId(productDTO.getId());

        product.setImagePaths(new ArrayList<>());

        Category category = new Category();
        category.setId(productDTO.getCategory().getId());
        category.setName(productDTO.getCategory().getName());
        product.setCategory(category);

        Student student = new Student();
        studentRepository.save(student);
        product.setStudent(student);
        productRepository.save(product);

        return productMapper.entityToDTO(product);
    }
}
