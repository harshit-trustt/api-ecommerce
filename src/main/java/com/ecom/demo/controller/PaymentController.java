package com.ecom.demo.controller;

import com.ecom.demo.dto.PaymentDto;
import com.ecom.demo.entity.Payment;
import com.ecom.demo.service.payment.PaymentService;
import com.ecom.demo.service.payment.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;



    @GetMapping
    public List<Payment> getAllPayment()
    {
        return paymentService.getAll();
    }


    @PostMapping
    public Payment addPayment(@RequestBody Payment payment)
    {
        return paymentService.addPayment(payment);
    }


//    @GetMapping("/{userId}")
//    public List<Payment> getPaymentsByUserId(@PathVariable int userId) {
//        return paymentServiceImpl.getPaymentsByUserId(userId);
//    }
//
//
//    @DeleteMapping("/{userId}")
//    public void deletePaymentsByUserId(@PathVariable int userId) {
//        paymentServiceImpl.deletePaymentsByUserId(userId);
//    }
}
