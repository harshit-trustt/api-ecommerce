package com.ecom.demo.service.customer;

import com.ecom.demo.entity.Customer;
import com.ecom.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
