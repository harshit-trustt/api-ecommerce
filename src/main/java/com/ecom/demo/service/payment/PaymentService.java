package com.ecom.demo.service.payment;

import com.ecom.demo.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAll();

    Payment addPayment(Payment payment);

//    public List<Payment> getPaymentsByUserId(int userId);
//
//    public void deletePaymentsByUserId(int userId);
}
