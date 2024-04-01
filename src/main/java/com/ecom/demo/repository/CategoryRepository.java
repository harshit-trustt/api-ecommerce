package com.ecom.demo.repository;

import com.ecom.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByCategoryType(String categoryName);
}
