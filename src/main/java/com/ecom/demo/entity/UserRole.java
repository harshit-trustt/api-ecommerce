package com.ecom.demo.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;
    private String role_name;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private Users user_id;
}
