package com.ecom.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String line1;
    private String line2;
    private String landmark;
    private String city;
    private String state;
    private String country;

    @Column //(unique = true)
    private String addressType;

    private int pincode;


    @ManyToOne
    private Users users;
}