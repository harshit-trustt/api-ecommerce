package com.ecom.demo.service.user;

import com.ecom.demo.entity.Users;
import com.ecom.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public Users save(Users tempUser) {
        return userRepository.save(tempUser);
    }

    public Users findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<Users> findUserById(int theId){
        return userRepository.findById(theId);
    }


    public Optional<Users> readUsers(int userId){
        return userRepository.findById(userId);
    }

    @Override
    public boolean userExists(int userId) {
        return userRepository.existsById(userId);
    }
}


