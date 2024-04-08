package com.ecom.demo.controller;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.dto.ProductDto;
import com.ecom.demo.dto.ProductResponseDto;
import com.ecom.demo.entity.Category;
import com.ecom.demo.entity.Product;

import com.ecom.demo.service.category.CategoryService;
import com.ecom.demo.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "insert the product", description = "Inserts the product")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully Inserted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Couldn't insert the Product")
    })
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
    @Operation(summary = "Get all Products", description = "Returns all Products")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Product was not found")
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        List<ProductResponseDto> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Operation(summary = "Update a Product by ProductID", description = "Updates a Product as per the ProductID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Updates"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Product was not found")
    })
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable int productId, @RequestBody Product product){
        if(Objects.nonNull(productService.readProduct(productId))){
            productService.updateProduct(productId, product);
            return new ResponseEntity<>(new ApiResponse(true, "product updated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "product does not exist"), HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete a Product by ProductID", description = "Deletes a Product as per the ProductID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Product was not found")
    })
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId){
        if((productService.readProduct(productId)).isPresent()){
            productService.deleteProductById(productId);
            return new ResponseEntity<>(new ApiResponse(true, "product deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "product does not exist"), HttpStatus.NOT_FOUND);
    }



    @Operation(summary = "Get a Product by ProductID", description = "Returns a Product as per the ProductID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Product was not found")
    })
    @GetMapping("/{categoryType}")
    public ResponseEntity<List<Product>> getProductsByCategoryType(@PathVariable String categoryType) {
        List<Product> products = productService.findProductsByCategoryType(categoryType);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}