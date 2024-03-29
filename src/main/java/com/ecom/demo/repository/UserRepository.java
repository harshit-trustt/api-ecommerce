package com.ecom.demo.repository;

import com.ecom.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users findUserByEmail(String email);
}

