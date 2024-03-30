package com.ecom.demo.controller;

import com.ecom.demo.entity.Category;
import com.ecom.demo.service.CategoryService;
import com.ecom.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public List<Category> allCategories(){
        return service.getCategories();
    }

}
