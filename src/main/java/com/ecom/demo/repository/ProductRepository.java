package com.ecom.demo.repository;

import com.ecom.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.categoryType = ?1")
    List<Product> findByCategoryType(String categoryType);
}
