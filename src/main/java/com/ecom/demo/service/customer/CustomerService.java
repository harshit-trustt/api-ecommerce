package com.ecom.demo.service.customer;

import com.ecom.demo.entity.Customer;

public interface CustomerService {
    public Customer getCustomerById(int id);
    public void addCustomer(Customer customer);
}
