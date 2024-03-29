package com.ecom.demo.entity;

import jakarta.persistence.*;

import java.util.List;

public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_item_id;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders orders;
    @ManyToMany
    @JoinColumn(name="product_id")
    private List<Products> products;
}
