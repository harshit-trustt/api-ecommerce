package com.ecom.demo.controller;

import com.ecom.demo.dto.AuthResponse;
import com.ecom.demo.entity.Customer;
import com.ecom.demo.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public AuthResponse getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }
}
