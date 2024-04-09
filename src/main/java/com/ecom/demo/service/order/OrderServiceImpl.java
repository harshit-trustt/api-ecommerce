package com.ecom.demo.service.order;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.dto.CartResponseDto;
import com.ecom.demo.entity.*;
import com.ecom.demo.repository.OrdersRepository;
import com.ecom.demo.service.address.AddressService;
import com.ecom.demo.service.cart.CartService;
import com.ecom.demo.service.orderItem.OrderItemService;
import com.ecom.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderItemService orderItemService;


    @Override
    public void addOrder(int userId, int addressId,String paymentType) {
        Orders orders = new Orders();
        List<CartResponseDto> list= cartService.getCartProductsResponse(cartService.getCartIdByUserId(userId));
        double totalAmount=0.0;
        for(CartResponseDto cartResponseDto : list){
            totalAmount+=cartResponseDto.getTotalPrice();
        }
        orders.setTotalAmount(totalAmount);
        Optional<Users> userOptional = userService.findUserById(userId);
        if (userOptional.isPresent()) {
        orders.setUsers(userOptional.get());
        }
        else {
            System.out.println("User not found");
        }
        Optional<Address> addressOptional = addressService.readAddress(addressId);
        if (addressOptional.isPresent()) {
            orders.setAddress(addressOptional.get());
        }
        else {
        System.out.println("Address not found");
    }
       // orders.setAddress(addressService.readAddress(addressId).get());
        orders.setPayment( new Payment(paymentType, LocalDate.now()));
        orders.setOrderDate(LocalDateTime.now());
        orders.setOrderStatus("Confirmed");
        orderRepository.save(orders);
        List<OrderItems> orderItems = new ArrayList<>();
        for(CartResponseDto x : list){
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct(x.getProduct());
            orderItem.setQuantity(x.getQuantity());
            orderItem.setOrders(orders);
            orderItemService.addOrder(orderItem);
            orderItems.add(orderItem);
            cartService.deleteProductById(orderItem.getProduct().getId(),userId);
        }
        orders.setOrderItems(orderItems);
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

    @Override
    public List<Orders> findOrdersByUserId(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }


//
//    @Override
//    public Page<Orders> findOrdersByUserId(int userId, Pageable pageable) {
//        return orderRepository.findOrdersByUserId(userId, pageable);
//    }
}
