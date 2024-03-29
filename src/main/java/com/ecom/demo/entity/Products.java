package com.ecom.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private String productDescription;
    private Long productPrice;
    private LocalDate addedOn;

    @ManyToMany(mappedBy = "products")
    private List<Category> category;

    @OneToMany(mappedBy = "products")
    private List<OrderItems> orderItems;
}