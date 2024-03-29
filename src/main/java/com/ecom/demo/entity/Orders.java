//package com.ecom.demo.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class Orders {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int order_id;
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private Users user;
//    @OneToOne
//    @JoinColumn(name="payment_id")
//    private Payment payment;
//    private LocalDateTime order_date;
//    private String order_status;
//    private Long Total_amount;
//    @OneToOne
//    @JoinColumn(name = "address_id")
//    private Address address;
//
//
//}
