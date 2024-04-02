package com.ecom.demo.service;

import com.ecom.demo.entity.Inventory;
import com.ecom.demo.repository.InventoryRepository;
import com.ecom.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;


    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }


    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }


    public void updateById(Inventory inventory, int id) {

        Optional<Inventory> inventoryOptional=inventoryRepository.findById(id);
        if (inventoryOptional.isPresent())
        {
            inventoryRepository.save(inventory);
        }
        else
        {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    public void deleteProductById(int id){
        Optional<Inventory> inventoryOptional=inventoryRepository.findById(id);
        if (inventoryOptional.isPresent())
        {
            inventoryRepository.deleteById(id);
        }
        else
        {
            System.out.println("Product with ID " + id + " not found.");
        }
    }
}
