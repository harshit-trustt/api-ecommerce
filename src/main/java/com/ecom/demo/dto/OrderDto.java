package com.ecom.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDto {

    private int paymentId;
    private int userId;
    private int addressId;
    private int reviewId;

    public OrderDto() {
    }

    public OrderDto(int paymentId, int userId, int addressId) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.addressId = addressId;
    }
    public OrderDto(int reviewId)
    {
        this.reviewId = reviewId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}
