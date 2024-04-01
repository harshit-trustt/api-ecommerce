package com.ecom.demo.service;

import com.ecom.demo.entity.Products;
import com.ecom.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;
    public List<Products> getProduct()
    {
        return productsRepository.findAll();
    }

//    public List<Products> getProductByCategory(int category_id)
//    {
//        return productsRepository.findByCategory(category_id);
//    }


    public List<Products> getProductsByCategoryType(String categoryType) {
        return productsRepository.findByCategoryType(categoryType);
    }

    public void updateById(Products products, int id) {
        Optional<Products> productOptional = productsRepository.findById(id);

        if (productOptional.isPresent()) {
            productsRepository.save(products);
        } else {
            // Handle the case where the product with the given ID does not exist
            System.out.println("Product with ID " + id + " not found.");
        }
    }

}
