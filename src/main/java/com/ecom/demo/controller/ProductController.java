package com.ecom.demo.controller;

import com.ecom.demo.entity.Products;
import com.ecom.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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


    @PutMapping("/product/{id}")
    public void UpdateProduct(@PathVariable int id,@RequestBody Products products)
    {
        productService.updateById(products,id);
    }
}
