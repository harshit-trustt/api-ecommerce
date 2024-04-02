package com.ecom.demo.repository;

import com.ecom.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

//    List<Payment> findByOrderUsersId(int userId);
//
//    void deleteByOrderUsersId(int userId);
}
