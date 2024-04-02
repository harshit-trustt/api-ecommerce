package com.ecom.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public CartItem(int quantity, Product product, Cart cart){
        this.setCart(cart);
        this.setQuantity(quantity);
        this.setProduct(product);
    }
}