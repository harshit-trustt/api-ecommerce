package com.ecom.demo.dto;

public class ProductResponseDto extends ProductDto{

    private String categoryType;

    public ProductResponseDto(int id, String productName, String productDescription, double productPrice, String imageUrl, int categoryId, String categiryType) {
        super(productName, productDescription, productPrice, imageUrl, categoryId);
        this.categoryType = categiryType;
        this.setId(id);
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
