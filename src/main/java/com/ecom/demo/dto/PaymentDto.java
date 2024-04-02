package com.ecom.demo.dto;

import java.time.LocalDate;

public class PaymentDto {

    private int id;

    private String payMethod;

    private LocalDate paidOn;

    public PaymentDto(String payMethod, LocalDate paidOn) {
        this.payMethod = payMethod;
        this.paidOn = paidOn;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public LocalDate getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(LocalDate paidOn) {
        this.paidOn = paidOn;
    }
}
