package com.ecom.demo.entity;

import jakarta.persistence.*;

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_item_id;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;

    private int quantity;

}
