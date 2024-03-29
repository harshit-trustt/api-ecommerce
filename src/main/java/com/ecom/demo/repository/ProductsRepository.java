package com.ecom.demo.repository;

import com.ecom.demo.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products,Integer> {

//    public List<Products> findByCategory(int category_id);
}
