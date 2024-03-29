//package com.ecom.demo.service;
//
//import com.ecom.demo.entity.Products;
//import com.ecom.demo.repository.ProductsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductService {
//
//    @Autowired
//    private ProductsRepository productsRepository;
//    public List<Products> getProduct()
//    {
//        return productsRepository.findAll();
//    }
//
//    public List<Products> getProductByCategory(int category_id)
//    {
//        return productsRepository.findByCategory(category_id);
//    }
//}
