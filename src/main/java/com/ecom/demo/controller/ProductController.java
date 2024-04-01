package com.ecom.demo.controller;

import com.ecom.demo.entity.Products;
import com.ecom.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductController {


    @Autowired
    private ProductService productService;


    // Get all Products
    @GetMapping("/products")
    public List<Products> getAllProducts()
    {
        return productService.getProduct();
    }


    // Get Products by Category
    @GetMapping("/products/category/{categoryType}")
    public List<Products> getProductsByCategoryType(@PathVariable String categoryType) {
        return productService.getProductsByCategoryType(categoryType);
    }

    //Add Product
    @PostMapping("/product")
    public ResponseEntity<Products> addProduct(@RequestBody Products prod){
        Products addedProd = productService.addProduct(prod);
        return new ResponseEntity<>(addedProd, HttpStatus.CREATED);
    }

    //Delete Product
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
