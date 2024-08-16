package com.online.retail.inventory.controller;

import com.online.retail.inventory.model.Inventory;
import com.online.retail.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Value("${inventory.message}")
    String message;
    @Autowired
    private InventoryService inventoryService;
    @GetMapping("/greet")
    public String getMessage(){
        return message;
    }
    @PostMapping("/")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory){
        return new ResponseEntity(inventoryService.addInventory(inventory), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> searchInventory(@PathVariable int id){
        return new ResponseEntity(inventoryService.searchInventory(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable int id, @RequestBody Inventory inventory){
        return new ResponseEntity(inventoryService.updateInventory(id, inventory), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable int id){
        return new ResponseEntity(inventoryService.deleteInventory(id), HttpStatus.OK);
    }
}
