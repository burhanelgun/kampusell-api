package com.bestudios.kampusellapi.controller;

import com.bestudios.kampusellapi.dto.ProductDTO;
import com.bestudios.kampusellapi.model.PhotoValue;
import com.bestudios.kampusellapi.model.ProductFilter;
import com.bestudios.kampusellapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/begin={begin}/end={end}")
    public List<ProductDTO> getAllProductsPage(@PathVariable("begin") Optional<Integer> begin,@PathVariable("end") Optional<Integer> end) {
        return productService.getAllProductsPage(begin,end);
    }

    @GetMapping("/categoryName={categoryName}")
    public List<ProductDTO> getProductsByCategoryName(@PathVariable("categoryName") Optional<String> categoryName) {
        return productService.getProductsByCategoryName(categoryName);
    }

    @GetMapping("/categoryName={categoryName}/begin={begin}/end={end}")
    public List<ProductDTO> getProductsByCategoryNamePage(@PathVariable("categoryName") Optional<String> categoryName) {
        return productService.getProductsByCategoryName(categoryName);
    }

    @GetMapping("/searchText={searchText}")
    public List<ProductDTO> getSearchTextProducts(@PathVariable("searchText") Optional<String> searchText) {
        return productService.getProductsBySearchText(searchText);
    }

    @GetMapping("/searchText={searchText}/begin={begin}/end={end}")
    public List<ProductDTO> getSearchTextProductsPage(@PathVariable("searchText") Optional<String> searchText) {
        return productService.getProductsBySearchText(searchText);
    }


    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.save(productDTO));
    }



    @PostMapping("/s")
    public ResponseEntity createProduct2(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.save(productDTO));
    }

    @PostMapping("/filter")
    public List<ProductDTO> createProduct2(@RequestBody Optional<ProductFilter> productFilter) {
        return productService.getFilteredProducts(productFilter);
    }

    @PostMapping("/findWithPhoto")
    public List<ProductDTO> findWithPhoto(@RequestBody Optional<PhotoValue> photoValue) {
        return productService.getWithPhoto(photoValue);
    }

    @PostMapping("/findWithPhoto/begin={begin}/end={end}")
    public List<ProductDTO> findWithPhotoPage(@RequestBody Optional<PhotoValue> photoValue) {
        return productService.getWithPhoto(photoValue);
    }

    @GetMapping("/username={username}")
    public List<ProductDTO> getMyProducts(@PathVariable("username") Optional<String> username) {
        return productService.getProductsByUsername(username);
    }
    @GetMapping("/username={username}/begin={begin}/end={end}")
    public List<ProductDTO> getMyProductsPage(@PathVariable("username") Optional<String> username) {
        return productService.getProductsByUsername(username);
    }

}