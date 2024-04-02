package com.ecom.demo.repository;

import com.ecom.demo.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    List<CartItem> findAllByCartId(int cartId);
}
