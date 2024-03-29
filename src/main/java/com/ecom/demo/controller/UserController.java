package com.ecom.demo.controller;

import com.ecom.demo.entity.Users;
import com.ecom.demo.response.AuthResponse;
import com.ecom.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Users tempUser){

        Map<String, Object> res = new HashMap<>();
        String msg = "";
        boolean suc = false;

        //checking is user already exists
        Users user = userService.findUserByEmail(tempUser.getEmail());
        if(user==null){
            //If not registered, register
            user = userService.save(tempUser);
            msg = "Registration Successful";
            suc = true;
            res.put("user", new AuthResponse(
                    user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber()));
        }
        else{
            msg = "User Already Exists";
        }
        res.put("message", msg);
        res.put("success", suc);
        //return response
        return res;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Users tempUser){

        Map<String, Object> res = new HashMap<>();
        String msg = "";
        boolean authenticated = false;

        //find User
        Users user = userService.findUserByEmail(tempUser.getEmail());
        if(user!=null){
            //authenticate user
            if(user.getPassword().equals(tempUser.getPassword())){
                msg = "Authentication Successful";
                authenticated = true;
                res.put("user", new AuthResponse(
                        user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber()));
            }
            else{
                msg = "Invalid Credentials";
            }
        }
        else{
            msg = "User Not Found";
        }
        res.put("message", msg);
        res.put("success", authenticated);

        //return response
        return res;
    }
}
