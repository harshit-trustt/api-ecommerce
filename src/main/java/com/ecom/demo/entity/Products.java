//package com.ecom.demo.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Products {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int product_id;
//    @ManyToMany
//    private List<Category> category;
//
//    private String product_name;
//    private String product_description;
//    private Long product_price;
//    private LocalDate added_on;
//}
//
//
//
