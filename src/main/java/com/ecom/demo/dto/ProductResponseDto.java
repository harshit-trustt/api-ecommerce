package com.ecom.demo.dto;

public class ProductResponseDto extends ProductDto{

    private String categoryType;

    public ProductResponseDto(String productName, String productDescription, double productPrice, String imageUrl, int categoryId, String categiryType) {
        super(productName, productDescription, productPrice, imageUrl, categoryId);
        this.categoryType = categiryType;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
