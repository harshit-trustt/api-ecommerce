package com.ecom.demo.service.customer;

import com.ecom.demo.dto.AuthResponse;
import com.ecom.demo.entity.Customer;
import com.ecom.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public AuthResponse getCustomerById(int id) {
        Customer cus = customerRepository.findById(id).get();
        return new AuthResponse(cus.getId(), cus.getUser().getName(), cus.getUser().getEmail(), cus.getUser().getPhoneNumber());
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
