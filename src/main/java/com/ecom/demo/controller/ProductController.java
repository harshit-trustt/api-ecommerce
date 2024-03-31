package com.ecom.demo.controller;

import com.ecom.demo.entity.Products;
import com.ecom.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService;




    @GetMapping("/products")
    public List<Products> getAllProducts()
    {
        return productService.getProduct();
    }

//    @GetMapping("/product/{category}")
//    public List<Products> getProductByCategory(@PathVariable int category_id)
//    {
//        return productService.getProductByCategory(category_id);
//    }


    @GetMapping("/products/category/{categoryType}")
    public List<Products> getProductsByCategoryType(@PathVariable String categoryType) {
        return productService.getProductsByCategoryType(categoryType);
    }
}
