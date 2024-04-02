package com.ecom.demo.service.product;

import com.ecom.demo.dto.ProductDto;
import com.ecom.demo.entity.Category;
import com.ecom.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public void addProduct(ProductDto productDto, Category category);

    public List<Product> listProducts();

    public Optional<Product> readProduct(int id);


    public void updateProduct(int productId, Product newProduct);

    public void deleteProductById(int productId);

    public List<Product> findProductsByCategoryType(String categoryType);

    public Product getProductById(int id);

}
