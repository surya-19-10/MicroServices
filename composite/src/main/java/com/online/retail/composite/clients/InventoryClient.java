package com.online.retail.composite.clients;

import com.online.retail.composite.core.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="inventory", url = "http://localhost:8082/inventory")
public interface InventoryClient {
    @PostMapping("/")
    ResponseEntity<Inventory> addInventory(Inventory inventory);
    @GetMapping("/{id}")
    ResponseEntity<Inventory> searchInventory(@PathVariable int id);
    @PutMapping("/{id}")
    ResponseEntity<Inventory> updateInventory(@PathVariable int id, @RequestBody Inventory inventory);
}
