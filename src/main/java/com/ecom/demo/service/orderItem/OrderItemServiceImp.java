package com.ecom.demo.service.orderItem;

import com.ecom.demo.entity.OrderItems;
import com.ecom.demo.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderItemServiceImp implements OrderItemService{
    @Autowired
    private OrderItemsRepository orderItemRepository;


    @Override
    public void addOrder(OrderItems orderItem) {
        orderItemRepository.save(orderItem);
    }
}
