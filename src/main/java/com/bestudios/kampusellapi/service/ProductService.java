package com.bestudios.kampusellapi.service;

import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.entity.Product;
import com.bestudios.kampusellapi.entity.Student;
import com.bestudios.kampusellapi.mapper.ProductMapper;
import com.bestudios.kampusellapi.model.ProductFilter;
import com.bestudios.kampusellapi.repository.CategoryRepository;
import com.bestudios.kampusellapi.repository.ProductRepository;
import com.bestudios.kampusellapi.repository.StudentRepository;
import com.bestudios.kampusellapi.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<ProductDTO> getAllProducts() {
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

        product.setImagePaths(productDTO.getImagePaths());

        Category category = categoryRepository.findByName(productDTO.getCategory().getName());
        product.setCategory(category);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<Student> student = studentRepository.findByUsername(username);
        product.setStudent(student.get());


        productRepository.save(product);

        return productMapper.entityToDTO(product);
    }

    public List<ProductDTO> getProductsByCategoryName(Optional<String> categoryNameOpt) {

        if(categoryNameOpt.isPresent()){
            String categoryName =categoryNameOpt.get();
            List<Product> products = productRepository.findAllByCategoryName(categoryName);
            return productMapper.entityToDTOList(products);

        } else{
            return null;

        }
    }

    public List<ProductDTO> getProductsBySearchText(Optional<String> searchTextOpt) {
        if(searchTextOpt.isPresent()){
            String searchText = searchTextOpt.get();
            List<Product> products = productRepository.findAllProductsBySearchText(searchText);
            return productMapper.entityToDTOList(products);

        } else{
            return null;

        }
    }

    public List<ProductDTO> getFilteredProducts(Optional<ProductFilter> productFilterOpt) {
        if(productFilterOpt.isPresent()){
            ProductFilter productFilter = productFilterOpt.get();
            List<Product> products = productRepository.findAllProductByFilter(productFilter);
            return productMapper.entityToDTOList(products);

        } else{
            return null;

        }
    }
}
