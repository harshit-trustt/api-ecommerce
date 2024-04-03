package com.ecom.demo.controller;


import com.ecom.demo.dto.AddressDto;
import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.entity.Address;
import com.ecom.demo.entity.Category;
import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.Users;
import com.ecom.demo.service.address.AddressService;
import com.ecom.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public List<Address> getAddressesByUserId(@PathVariable int userId) {
        return addressService.getAddressesByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addAddress(@RequestBody AddressDto addressDto)
    {
        Optional<Users> optionalUsers = userService.readUsers(addressDto.getUserId());
        if(!optionalUsers.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "user doesnot exist"), HttpStatus.CONFLICT);
        }
        Users users = optionalUsers.get();
        addressService.addAddress(addressDto, users);
        return new ResponseEntity<>(new ApiResponse(true, "address has been added"), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable int id, @RequestBody Address address){
        if(Objects.nonNull(addressService.readAddress(id))){
            addressService.updateAddress(id, address);
            return new ResponseEntity<>(new ApiResponse(true, "address updated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "address does not exist"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable int id){
        if((addressService.readAddress(id)).isPresent()){
            addressService.deleteAddressById(id);
            return new ResponseEntity<>(new ApiResponse(true, "address deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "address does not exist"), HttpStatus.NOT_FOUND);
    }
}
