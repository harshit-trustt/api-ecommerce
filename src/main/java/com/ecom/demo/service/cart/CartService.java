package com.ecom.demo.service.cart;

import com.ecom.demo.dto.CartDto;
import com.ecom.demo.dto.CartResponseDto;
import com.ecom.demo.entity.CartItem;

import java.util.List;


public interface CartService {
    public int getCartIdByUserId(int userId);


    public void addToCart(CartDto cartDto, int cartId);
    public boolean updateCart(CartDto cartDto);

    public boolean cartHasProduct(int pId, int cartId);
    public List<CartItem> getCartProducts(int cartId);
    public boolean deleteProductById(int pId, int userId);
    public List<CartResponseDto> getCartProductsResponse(int cartId);

}
