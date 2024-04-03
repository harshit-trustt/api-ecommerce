package com.ecom.demo.service.address;

import com.ecom.demo.entity.Address;

import java.util.List;

public interface AddressService {

    public List<Address> getAddressesByUserId(int userId);

    Address addAddress(Address address);

//    void updateAddress(Address address, int id);

    public void deleteAddressByUserId(int userId);
}
