package com.ecom.demo.controller;

import com.ecom.demo.dto.PaymentDto;
import com.ecom.demo.entity.Payment;
import com.ecom.demo.service.payment.PaymentService;
import com.ecom.demo.service.payment.PaymentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;



    @Operation(summary = "Get all Payments", description = "Returns all Payments")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Payment was not found")
    })
    @GetMapping
    public List<Payment> getAllPayment()
    {
        return paymentService.getAll();
    }


    @Operation(summary = "insert the payment", description = "Inserts the payment")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully Inserted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Couldn't insert the payment")
    })
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
