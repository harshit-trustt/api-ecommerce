package com.ecom.demo.service.order;

import com.ecom.demo.entity.Orders;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    void addOrder(Orders orders);

    List<Orders> listOrders();

    Optional<Orders> readOrder(int id);

    void updateOrder(int orderId, Orders newOrders);

    void deleteOrderById(int orderId);


 //   public Page<Orders> findOrdersByUserId(int userId, Pageable pageable);


}
