package com.ecom.demo.controller;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.entity.Category;
import com.ecom.demo.service.category.CategoryService;
import com.ecom.demo.service.category.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<String> getCategoryById(@PathVariable int categoryID){
        Category category = categoryService.readCategory(categoryID).get();
        if(Objects.nonNull(category)){
            return new ResponseEntity<>(category.getCategoryType(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        if(Objects.nonNull(categoryService.readCategory(category.getCategoryType()))){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "category created"), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable int categoryID, @RequestBody Category category){
        if(Objects.nonNull(categoryService.readCategory(categoryID))){
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "category does not exists"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{categoryID}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryID){
        if(Objects.nonNull(categoryService.readCategory(categoryID))){
            categoryService.deleteCategoryById(categoryID);
            return new ResponseEntity<>(new ApiResponse(true, "category deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }
}
