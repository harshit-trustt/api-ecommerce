package com.ecom.demo.service.address;

import com.ecom.demo.dto.AddressDto;
import com.ecom.demo.dto.ProductDto;
import com.ecom.demo.entity.Address;
import com.ecom.demo.entity.Category;
import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.Users;
import com.ecom.demo.repository.AddressRepository;
import com.ecom.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Address> getAddressesByUserId(int userId) {
        return addressRepository.findByUsersId(userId);
    }

    @Override
    public void addAddress(AddressDto addressDto,Users users) {
        Address address = getAddressFromDto(addressDto, users);
        addressRepository.save(address);
    }


    @Transactional
    public void deleteAddressByUserId(int userId) {
        addressRepository.deleteByUsersId(userId);
    }



    public static Address getAddressFromDto(AddressDto addressDto, Users users){
        Address address = new Address();
        address.setUsers(users);
        address.setLine1(addressDto.getLine1());
        address.setLine2(addressDto.getLine2());
        address.setLandmark(addressDto.getLandmark());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setAddressType(addressDto.getAddressType());
        address.setPincode(addressDto.getPincode());
        return address;
    }


    public Optional<Address> readAddress(int id){
        return addressRepository.findById(id);
    }

    @Override
    public void updateAddress(int id, Address address) {
        Address address1 = readAddress(id).get();
        address1.setLine1(address.getLine1());
        address1.setLine2(address.getLine2());
        address1.setLandmark(address.getLandmark());
        address1.setCity(address.getCity());
        address1.setState(address.getState());
        address1.setCountry(address.getCountry());
        address1.setAddressType(address.getAddressType());
        address1.setPincode(address.getPincode());
        addressRepository.save(address1);
    }

    @Override
    public void deleteAddressById(int id) {
       addressRepository.deleteById(id);
    }
}
