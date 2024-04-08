package com.ecom.demo.controller;

import com.ecom.demo.dto.AuthResponse;
import com.ecom.demo.entity.Customer;
import com.ecom.demo.service.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get a Customer by ID", description = "Returns a Customer as per the ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Customer was not found")
    })
    @GetMapping("/{id}")
    public AuthResponse getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }
}
