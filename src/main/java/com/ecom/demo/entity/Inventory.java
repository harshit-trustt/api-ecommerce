//package com.ecom.demo.entity;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Inventory {
//    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private int quantity;
//
//    @OneToOne(mappedBy = "inventory")
//    @JsonManagedReference(value = "inventory")
//    private Product product;
//
//    public Inventory(int id) {
//        this.id = id;
//    }
//
//
//    public Inventory() {
//    }
//
//    public Inventory(int quantity, Product product) {
//        this.quantity = quantity;
//        this.product = product;
//    }
//
//
//}