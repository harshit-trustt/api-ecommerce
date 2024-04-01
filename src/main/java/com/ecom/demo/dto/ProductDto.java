package com.ecom.demo.dto;

import java.time.LocalDate;

public class ProductDto {
    private int id;
    private String productName;
    private String productDescription;
    private double productPrice;
    private String imageUrl;
    private int categoryId;
    private final LocalDate addedOn;

    public ProductDto(String productName, String productDescription, double productPrice, String imageUrl, int categoryId) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.addedOn = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }
}
