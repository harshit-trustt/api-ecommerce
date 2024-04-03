package com.ecom.demo.service.order;

import com.ecom.demo.dto.CartResponseDto;
import com.ecom.demo.entity.OrderItems;
import com.ecom.demo.entity.Orders;
import com.ecom.demo.entity.Payment;
import com.ecom.demo.repository.OrdersRepository;
import com.ecom.demo.service.address.AddressService;
import com.ecom.demo.service.cart.CartService;
import com.ecom.demo.service.orderItem.OrderItemService;
import com.ecom.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

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


    private CartService cartService;
    private AddressService addressService;
    private UserService userService;
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
        orders.setUsers(userService.findUserById(userId).get());
        orders.setAddress(addressService.readAddress(addressId).get());
        orders.setPayment( new Payment(paymentType, LocalDate.now()));
        orders.getOrderDate();

        List<OrderItems> orderItems = new ArrayList<>();
        for(CartResponseDto cartResponseDto : list){
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct(cartResponseDto.getProduct());
            orderItem.setQuantity(cartResponseDto.getQuantity());
            orderItem.setOrders(orders);
            orderItems.add(orderItem);
            cartService.deleteProductById(orderItem.getProduct().getId(),userId);
            orderItemService.addOrder(orderItem);
        }
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
