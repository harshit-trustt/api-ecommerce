package com.ecom.demo.service;

import com.ecom.demo.entity.Users;
import com.ecom.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users save(Users tempUser) {
        return userRepository.save(tempUser);
    }
}
