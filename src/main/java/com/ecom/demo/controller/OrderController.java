package com.ecom.demo.controller;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.dto.OrderDto;
import com.ecom.demo.entity.Orders;
import com.ecom.demo.service.order.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "insert the order", description = "Inserts the order")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully Inserted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Couldn't insert the order")
    })
    @PostMapping
    public ResponseEntity<ApiResponse> addOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto.getUserId(), orderDto.getAddressId(), orderDto.getPaymentMethod());
        return new ResponseEntity<>(new ApiResponse(true, "Order added"), HttpStatus.CREATED);
    }


    @Operation(summary = "Get all Orders", description = "Returns all Orders")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Order was not found")
    })
    @GetMapping
    public ResponseEntity<List<Orders>> getOrders() {
        List<Orders> orders = orderService.listOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Update an order
    @Operation(summary = "Update a Order by OrderID", description = "Updates a Order as per the OrderID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Updates"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Order was not found")
    })
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable int orderId, @RequestBody Orders orders) {
        if (Objects.nonNull(orderService.readOrder(orderId))) {
            orderService.updateOrder(orderId, orders);
            return new ResponseEntity<>(new ApiResponse(true, "Order updated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "Order does not exist"), HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Get a Order by OrderID", description = "Returns a Order as per the OrderID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Order was not found")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrder(@PathVariable int orderId) {
        Orders order = orderService.readOrder(orderId).get();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

