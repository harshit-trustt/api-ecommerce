package com.ecom.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime orderDate;
    private String orderStatus;
    private double totalAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="payment_id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users usersId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;


    public Orders() {
    }

    public Orders(LocalDateTime orderDate, String orderStatus, double totalAmount, Users users, Address address, List<OrderItems> orderItems, Review review) {
        this.orderDate = LocalDateTime.now();
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.usersId= users;
        this.address = address;
        this.orderItems = orderItems;
        this.review = review;
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

    public Users getUsers() {
        return usersId;
    }

    public void setUsers(Users users) {
        this.usersId = users;
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
}