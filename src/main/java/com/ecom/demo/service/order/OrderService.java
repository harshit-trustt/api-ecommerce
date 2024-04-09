package com.ecom.demo.service.order;

import com.ecom.demo.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {


    void addOrder(int userId, int addressId,String paymentType);

    List<Orders> listOrders();

    Optional<Orders> readOrder(int id);

    void updateOrder(int orderId, Orders newOrders);

    void deleteOrderById(int orderId);

    List<Orders> findOrdersByUserId(int userId);


    //   public Page<Orders> findOrdersByUserId(int userId, Pageable pageable);


}
