package com.ecom.demo.service.product;

import com.ecom.demo.dto.ProductDto;
import com.ecom.demo.dto.ProductResponseDto;
import com.ecom.demo.entity.Category;
import com.ecom.demo.entity.Product;
import com.ecom.demo.repository.ProductRepository;
import com.ecom.demo.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;


    public void addProduct(ProductDto productDto, Category category){
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public static Product getProductFromDto(ProductDto productDto, Category category){
        Product product = new Product();
        product.setCategory(category);
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductDescription(productDto.getProductDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setAddedOn(LocalDate.now());
        product.setQuantity(productDto.getQuantity());
        return product;
    }

    public Product getProductById(int id){
        return productRepository.findById(id).get();
    }

    public List<ProductResponseDto> listProducts()
    {
        List<Product> tempList = productRepository.findAll();
        List<ProductResponseDto> list = new ArrayList<>();
        for(Product x : tempList){
            //create using constructor
            ProductResponseDto productResponseDto = new ProductResponseDto(x.getId(), x.getProductName(), x.getProductDescription(), x.getProductPrice(), x.getImageUrl(), x.getCategory().getId(), x.getCategory().getCategoryType(), x.getQuantity());
            list.add(productResponseDto);
        }
        return list;
    }

    public Optional<Product> readProduct(int id){
        return productRepository.findById(id);
    }

    public void updateProduct(int productId, Product newProduct){
        Product product = readProduct(productId).get();
        product.setProductName(newProduct.getProductName());
        product.setProductPrice(newProduct.getProductPrice());
        product.setProductDescription(newProduct.getProductDescription());
        product.setImageUrl(newProduct.getImageUrl());
        product.setAddedOn(newProduct.getAddedOn());
        productRepository.save(product);
    }

    public void deleteProductById(int productId){
        productRepository.deleteById(productId);
    }


    public List<Product> findProductsByCategoryType(String categoryType) {
        return productRepository.findByCategoryType(categoryType);
    }


    /*

    public Products addProduct(Products prod){
        return productsRepository.save(prod);
    }


    public void deleteProductById(int id){
        Optional<Products> productOptional = productsRepository.findById(id);

        if (productOptional.isPresent()) {
            productsRepository.deleteById(id);
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }


    public List<Products> getProductsByCategoryType(String categoryType) {
        return productsRepository.findByCategoryType(categoryType);
    }

    public void updateById(Products products, int id) {
        Optional<Products> productOptional = productsRepository.findById(id);

        if (productOptional.isPresent()) {
            productsRepository.save(products);
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

     */

}
