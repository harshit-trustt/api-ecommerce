package com.ecom.demo.service.address;

import com.ecom.demo.dto.AddressDto;
import com.ecom.demo.entity.Address;
import com.ecom.demo.entity.Users;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    public List<Address> getAddressesByUserId(int userId);

    public void addAddress(AddressDto addressDto, Users users);

//    void updateAddress(Address address, int id);

    public void deleteAddressByUserId(int userId);


    public Optional<Address> readAddress(int id);

    void updateAddress(int id, Address address);

    void deleteAddressById(int id);
}
