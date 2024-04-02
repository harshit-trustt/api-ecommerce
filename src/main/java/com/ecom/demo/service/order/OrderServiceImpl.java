package com.ecom.demo.service.order;

import com.ecom.demo.entity.Orders;
import com.ecom.demo.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersRepository orderRepository;

    @Override
    public void addOrder(Orders orders) {
        orderRepository.save(orders);
    }

    @Override
    public List<Orders> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orders> readOrder(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void updateOrder(int orderId, Orders newOrders) {
        Orders orders = readOrder(orderId).get();
        // Update fields here
        orderRepository.save(orders);
    }

    @Override
    public void deleteOrderById(int orderId) {
        orderRepository.deleteById(orderId);
    }
//
//    @Override
//    public Page<Orders> findOrdersByUserId(int userId, Pageable pageable) {
//        return orderRepository.findOrdersByUserId(userId, pageable);
//    }
}
