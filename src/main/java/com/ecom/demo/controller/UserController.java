package com.ecom.demo.controller;

import com.ecom.demo.entity.Users;
import com.ecom.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    //hello 
    @Autowired
    private UserService userService;
// new line
    @PostMapping("/register")
    public Users register(@RequestBody Users tempUser){
        return userService.save(tempUser);
    }
}
