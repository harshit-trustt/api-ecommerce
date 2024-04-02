package com.ecom.demo.dto;

import com.ecom.demo.entity.Cart;
import com.ecom.demo.entity.CartItem;
import com.ecom.demo.entity.Product;

public class CartResponseDto extends CartItem {
    private double totalPrice;

    public CartResponseDto(){}

    public CartResponseDto(int quantity, Product product, Cart cart, int price){
        super(quantity, product, cart);
        this.setTotalPrice(price);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
