package com.ecom.demo.repository;

import com.ecom.demo.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {

//    @Query("SELECT o FROM Orders o WHERE o.users.id = ?1")
//    Page<Orders> findOrdersByUserId(int userId, Pageable pageable);
}
