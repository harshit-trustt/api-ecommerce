package com.ecom.demo.service.user;

import com.ecom.demo.entity.Users;

import java.util.Optional;

public interface UserService {

    public Users save(Users tempUser);

    public Users findUserByEmail(String email);

    public Optional<Users> findUserById(int theId);
}
