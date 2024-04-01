package com.ecom.demo.dto;

import com.ecom.demo.entity.Cart;
import jakarta.validation.constraints.NotNull;

public class CartDto {
    private @NotNull int id;
    private @NotNull int userId;
    private @NotNull int pId;
    private @NotNull int quantity;

    public CartDto(){}

    public CartDto(Cart cart){
        this.setId(cart.getId());
        this.setUserId(cart.getUser().getId());
        this.setId(cart.getId());
        this.setId(cart.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
