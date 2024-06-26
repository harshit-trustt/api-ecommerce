package com.ecom.demo.controller;

import com.ecom.demo.entity.Customer;
import com.ecom.demo.entity.Users;
import com.ecom.demo.dto.AuthResponse;
import com.ecom.demo.service.customer.CustomerService;
import com.ecom.demo.service.user.UserService;
import com.ecom.demo.service.user.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Register a User", description = "Registers a User")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully Inserted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Couldn't add the User")
    })
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Users tempUser) {

        Map<String, Object> res = new HashMap<>();
        String msg = "";
        boolean suc = false;

        //checking is user already exists
        Users user = userService.findUserByEmail(tempUser.getEmail());
        if (user == null) {
            //If not registered, register
            user = userService.save(tempUser);
            msg = "Registration Successful";
            suc = true;
            res.put("user", new AuthResponse(
                    user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber()));
            Customer customer = new Customer(user);
            customerService.addCustomer(customer);
        } else {
            msg = "User Already Exists";
        }
        res.put("message", msg);
        res.put("success", suc);
        //return response
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Login a User", description = "Logs in a User")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Logged In"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Invalid Credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Users tempUser) {

        Map<String, Object> res = new HashMap<>();
        String msg = "";
        boolean authenticated = false;

        //find User
        Users user = userService.findUserByEmail(tempUser.getEmail());
        if (user != null) {
            //authenticate user
            if (user.getPassword().equals(tempUser.getPassword())) {
                msg = "Authentication Successful";
                authenticated = true;
                res.put("user", new AuthResponse(
                        user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber()));
            } else {
                msg = "Invalid Credentials";
            }
        } else {
            msg = "User Not Found";
        }
        res.put("message", msg);
        res.put("success", authenticated);

        //return response
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get a User by UserID", description = "Returns a User as per the UserID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    @GetMapping("/user/{id}")
    public Map<String, Object> getUserDetail(@PathVariable int id){

        Map<String, Object> res = new HashMap<>();
        String msg = "";
        boolean suc = false;

        Optional<Users> user = userService.findUserById(id);
        if(user.isPresent()){
            msg = "User found";
            suc = true;
            res.put("user", new AuthResponse(user.get().getId(), user.get().getName(), user.get().getEmail(), user.get().getPhoneNumber()));
        }
        else{
            msg = "Please Login to access this resource";
        }
        res.put("message", msg);
        res.put("success", suc);
        return res;
    }


    @Operation(summary = "Get a User by Email", description = "Returns a User as per the Email")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    @GetMapping("/user/email/{email}")
    public int getUserIdByEmail(@PathVariable String email){
        return userService.findUserByEmail(email).getId();
    }
}