package com.ecom.demo.repository;

import com.ecom.demo.entity.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByUsers_Id(int userId);

    @Modifying
    @Transactional
    @Query("delete from Address a where a.users.id = ?1")
    void deleteByUsers_Id(int userId);
}
