//package com.ecom.demo.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int cart_id;
//
//    @OneToOne
//    @JoinColumn(name="user_id")
//    private Users user;
//    private String total_amount;
//}
