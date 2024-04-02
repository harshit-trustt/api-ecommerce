//package com.ecom.demo.controller;
//
//import com.ecom.demo.entity.Inventory;
//import com.ecom.demo.service.inventory.InventoryServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/inventory")
//public class InventoryController {
//
//    @Autowired
//    private InventoryServiceImpl inventoryServiceImpl;
//
//
//    @GetMapping
//    public List<Inventory> getAll()
//    {
//        return inventoryServiceImpl.getAll();
//    }
//
//
//    @PostMapping
//    public Inventory saveInventory(@RequestBody Inventory inventory)
//    {
//        return inventoryServiceImpl.saveInventory(inventory);
//    }
//
//
//    @PutMapping("/{id}")
//    public void updateInventory(@PathVariable int id, @RequestBody Inventory inventory)
//    {
//        inventoryServiceImpl.updateById(inventory,id);
//    }
//
//
//    @DeleteMapping("/product/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable int id){
//        inventoryServiceImpl.deleteProductById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
//
//
//
//
//
//
//
