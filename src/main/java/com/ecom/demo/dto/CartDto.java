package com.ecom.demo.dto;

import com.ecom.demo.entity.Cart;
import jakarta.validation.constraints.NotNull;

public class CartDto {
    private int userId;
    private int pId;
    private int quantity;

    public CartDto(){}

    public CartDto(Cart cart){
        this.setUserId(cart.getUser().getId());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
