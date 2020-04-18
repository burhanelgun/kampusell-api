package com.bestudios.kampusellapi.repository;


import com.bestudios.kampusellapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);


    @Query("select p from Product p where p.name like %?1%")
    List<Product> findAllProductsBySearchText(String searchText);

}