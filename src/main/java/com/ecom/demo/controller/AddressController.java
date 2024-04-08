package com.ecom.demo.controller;


import com.ecom.demo.dto.AddressDto;
import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.entity.Address;
import com.ecom.demo.entity.Category;
import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.Users;
import com.ecom.demo.service.address.AddressService;
import com.ecom.demo.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get a Address by Userid", description = "Returns a Address as per the Userid")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Employee was not found")
    })
    @GetMapping("/{userId}")
    public List<Address> getAddressesByUserId(@PathVariable int userId) {
        return addressService.getAddressesByUserId(userId);
    }

    @Operation(summary = "insert the address", description = "Inserts the address")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully Inserted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Couldn't insert the address")
    })
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

    @Operation(summary = "Update a Address by id", description = "Updates a Address as per the id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Address was not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable int id, @RequestBody Address address){
        if(Objects.nonNull(addressService.readAddress(id))){
            addressService.updateAddress(id, address);
            return new ResponseEntity<>(new ApiResponse(true, "address updated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "address does not exist"), HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Delete a Address by id", description = "Deletes a Address as per the id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Address was not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable int id){
        if((addressService.readAddress(id)).isPresent()){
            addressService.deleteAddressById(id);
            return new ResponseEntity<>(new ApiResponse(true, "address deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "address does not exist"), HttpStatus.NOT_FOUND);
    }
}
