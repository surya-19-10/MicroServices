package com.online.retail.composite.clients;

import com.online.retail.composite.core.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="inventory")
public interface InventoryClient {
    @PostMapping("/inventory/")
    ResponseEntity<Inventory> addInventory(Inventory inventory);
    @GetMapping("/inventory/{id}")
    ResponseEntity<Inventory> searchInventory(@PathVariable int id);
    @PutMapping("/inventory/{id}")
    ResponseEntity<Inventory> updateInventory(@PathVariable int id, @RequestBody Inventory inventory);
}
