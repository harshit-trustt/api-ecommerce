package com.ecom.demo.service;

import com.ecom.demo.entity.Category;
import com.ecom.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }

    public Category readCategory(String categoryName){
        return categoryRepository.findByCategoryType(categoryName);
    }

    public Optional<Category> readCategory(int categoryID){
        return categoryRepository.findById(categoryID);
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(int categoryID, Category newCategory){
        Category category = categoryRepository.findById(categoryID).get();
        category.setCategoryType(newCategory.getCategoryType());
        categoryRepository.save(category);
    }

    public void deleteCategoryById(int categoryID){
        categoryRepository.deleteById(categoryID);
    }
}
