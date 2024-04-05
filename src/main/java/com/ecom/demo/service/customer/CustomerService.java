package com.ecom.demo.service.customer;

import com.ecom.demo.dto.AuthResponse;
import com.ecom.demo.entity.Customer;

public interface CustomerService {
    public AuthResponse getCustomerById(int id);
    public void addCustomer(Customer customer);
}
