package com.ecom.demo.dto;

public class CategoryDto {

    private int id;

    private String categoryType;

    public CategoryDto(String categoryType) {
        this.categoryType = categoryType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
