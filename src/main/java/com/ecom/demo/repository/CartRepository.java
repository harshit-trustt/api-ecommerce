package com.ecom.demo.repository;

import com.ecom.demo.entity.Cart;
import com.ecom.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUser(Users user);
}
