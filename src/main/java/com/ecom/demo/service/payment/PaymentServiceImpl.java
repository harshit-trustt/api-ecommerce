package com.ecom.demo.service.payment;

import com.ecom.demo.entity.Payment;
import com.ecom.demo.entity.Users;
import com.ecom.demo.repository.PaymentRepository;
import com.ecom.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository usersRepository;


    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

//    public List<Payment> getPaymentsByUserId(int userId) {
//        return paymentRepository.findByOrderUsersId(userId);
//    }
//
//
//    @Transactional
//    public void deletePaymentsByUserId(int userId) {
//        Optional<Payment> paymentOptional = paymentRepository.findById(userId);
//        paymentOptional.ifPresent(payment -> paymentRepository.delete(payment));
//    }

}
