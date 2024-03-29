package com.ecom.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders orders;


    @ManyToMany
    @JoinTable(
            name = "order_items_products", // Name of the join table
            joinColumns = @JoinColumn(name = "order_item_id"), // Column in the join table that refers to the OrderItems entity
            inverseJoinColumns = @JoinColumn(name = "product_id") // Column in the join table that refers to the Products entity
    )
    private List<Products> products;
}