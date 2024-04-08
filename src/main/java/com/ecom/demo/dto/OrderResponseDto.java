package com.ecom.demo.dto;

import com.ecom.demo.entity.Address;
import com.ecom.demo.entity.OrderItems;
import com.ecom.demo.entity.Payment;
import com.ecom.demo.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private int id;
    private LocalDateTime orderDate;
    private String orderStatus;
    private double totalAmount;
    private Payment payment;
    private Address address;
    private List<OrderItems> orderItems;
    private Review review;
    private AuthResponse user;

    public OrderResponseDto(int id, LocalDateTime orderDate, String orderStatus, double totalAmount, Payment payment, Address address, List<OrderItems> orderItems, Review review, AuthResponse user) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.payment = payment;
        this.address = address;
        this.orderItems = orderItems;
        this.review = review;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public AuthResponse getUser() {
        return user;
    }

    public void setUser(AuthResponse user) {
        this.user = user;
    }
}
