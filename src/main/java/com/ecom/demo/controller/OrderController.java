package com.ecom.demo.controller;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.entity.Orders;
import com.ecom.demo.service.order.OrderService;

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
    @PostMapping("/")
    public ResponseEntity<ApiResponse> addOrder(@RequestBody Orders orders) {
        orderService.addOrder(orders);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }


    @GetMapping("/")
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


    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable int orderId) {
        if (orderService.readOrder(orderId).isPresent()) {
            orderService.deleteOrderById(orderId);
            return new ResponseEntity<>(new ApiResponse(true, "Order deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "Order does not exist"), HttpStatus.NOT_FOUND);
    }
}
