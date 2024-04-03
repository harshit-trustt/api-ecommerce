package com.ecom.demo.dto;

public class OrderDto {
    private int userId;
    private int addressId;
    private String paymentMethod;

    public OrderDto(int userId, int addressId, String paymentMethod) {
        this.userId = userId;
        this.addressId = addressId;
        this.paymentMethod = paymentMethod;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}