package com.ecom.demo.controller;


import com.ecom.demo.entity.Address;
import com.ecom.demo.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}")
    public List<Address> getAddressesByUserId(@PathVariable int userId) {
        return addressService.getAddressesByUserId(userId);
    }

    @PostMapping
    public Address addAddress(@RequestBody Address address)
    {
        return addressService.addAddress(address);
    }


//    @PutMapping("{userId}")
//    public Address updateAddressByUserId(@PathVariable int userId, @RequestBody Address updatedAddress) {
//        return addressService.updateAddressByUserId(userId, updatedAddress);
//    }

    @DeleteMapping("/{userId}")
    public void deleteAddressByUserId(@PathVariable int userId) {
        addressService.deleteAddressByUserId(userId);
    }
}
