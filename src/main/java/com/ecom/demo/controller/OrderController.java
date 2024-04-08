package com.ecom.demo.controller;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.dto.OrderDto;
import com.ecom.demo.entity.Orders;
import com.ecom.demo.service.order.OrderService;

import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Add new order
    @PostMapping
    public ResponseEntity<ApiResponse> addOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto.getUserId(), orderDto.getAddressId(), orderDto.getPaymentMethod());
        return new ResponseEntity<>(new ApiResponse(true, "Order added"), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Orders>> getOrders() {
        List<Orders> orders = orderService.listOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Update an order
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable int orderId, @RequestBody Orders orders) {
        if (Objects.nonNull(orderService.readOrder(orderId))) {
            orderService.updateOrder(orderId, orders);
            return new ResponseEntity<>(new ApiResponse(true, "Order updated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "Order does not exist"), HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrder(@PathVariable int orderId) {
        Orders order = orderService.readOrder(orderId).get();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

