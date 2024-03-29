package com.ecom.demo.entity;

import jakarta.persistence.*;

public class Inventory {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invetory_id;
    @ManyToMany
    @JoinColumn(name="category_id")
    private Category category;
    @OneToMany
    @JoinColumn(name="product_id")
    private Products products;
    private int quantity;
}
