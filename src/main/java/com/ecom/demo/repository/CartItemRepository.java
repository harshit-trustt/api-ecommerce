package com.ecom.demo.repository;

import com.ecom.demo.entity.Cart;
import com.ecom.demo.entity.CartItem;
import com.ecom.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findAllByCartId(int cartId);
    void deleteByProductId(int pId);

    CartItem getCartItemByProductId(int pId);
}
