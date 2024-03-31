package com.ecom.demo.repository;

import com.ecom.demo.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products,Integer> {

    @Query("SELECT p FROM Products p JOIN p.category c WHERE c.categoryType = ?1")
    List<Products> findByCategoryType(String categoryType);
}
