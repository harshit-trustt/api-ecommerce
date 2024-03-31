package com.ecom.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private double productPrice;
    private LocalDate addedOn;

    // Add the category field to hold the reference to the Category entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id") // This indicates the foreign key column in Products table
    @JsonBackReference(value = "category")
    private Category category;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    @JsonBackReference(value = "inventory")
    private Inventory inventory;

//    @OneToOne
//    private CartItem cartItem;

//    @OneToOne(mappedBy = "product")
//    private OrderItems orderItem;
}