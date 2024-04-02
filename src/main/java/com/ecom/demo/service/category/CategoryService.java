package com.ecom.demo.service.category;

import com.ecom.demo.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

     List<Category> listCategories();

     Category readCategory(String categoryName);

     Optional<Category> readCategory(int categoryID);

     void createCategory(Category category);

    void updateCategory(int categoryID, Category newCategory);

    void deleteCategoryById(int categoryID);

}
