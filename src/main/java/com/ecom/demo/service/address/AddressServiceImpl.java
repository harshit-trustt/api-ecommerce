package com.ecom.demo.service.address;

import com.ecom.demo.entity.Address;
import com.ecom.demo.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddressesByUserId(int userId) {
        return addressRepository.findByUsers_Id(userId);
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }


    @Transactional
    public void deleteAddressByUserId(int userId) {
        addressRepository.deleteByUsers_Id(userId);
    }
}
