package com.bestudios.kampusellapi.repository;


import com.bestudios.kampusellapi.entity.Product;
import com.bestudios.kampusellapi.model.ProductFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);


    @Query("select p from Product p where p.name like %?1%")
    List<Product> findAllProductsBySearchText(String searchText);

    @Query("select p from Product p WHERE p.category.name = :#{#productFilter.category.name} and p.price BETWEEN :#{#productFilter.minPrice} AND :#{#productFilter.maxPrice}")
    List<Product> findAllProductByFilter(@Param("productFilter") ProductFilter productFilter);

    List<Product> findAllByCategoryName(String categoryName);
}