package com.ecom.demo.controller;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.dto.ProductDto;
import com.ecom.demo.dto.ProductResponseDto;
import com.ecom.demo.entity.Category;
import com.ecom.demo.entity.Product;

import com.ecom.demo.service.category.CategoryService;
import com.ecom.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    //Get All Products
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        List<ProductResponseDto> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable int productId, @RequestBody Product product){
        if(Objects.nonNull(productService.readProduct(productId))){
            productService.updateProduct(productId, product);
            return new ResponseEntity<>(new ApiResponse(true, "product updated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "product does not exist"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId){
        if((productService.readProduct(productId)).isPresent()){
            productService.deleteProductById(productId);
            return new ResponseEntity<>(new ApiResponse(true, "product deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "product does not exist"), HttpStatus.NOT_FOUND);
    }



    @GetMapping("/{categoryType}")
    public ResponseEntity<List<Product>> getProductsByCategoryType(@PathVariable String categoryType) {
        List<Product> products = productService.findProductsByCategoryType(categoryType);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}