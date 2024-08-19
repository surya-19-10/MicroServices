package com.online.retail.composite.clients;

import com.online.retail.composite.core.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cart", url = "http://localhost:8083/cart")
public interface CartClient {
    @PostMapping("/")
    ResponseEntity<Cart> saveCart(Cart cart);
    @GetMapping("/{id}")
    ResponseEntity<Cart> getCart(@PathVariable int id);
    @PutMapping("/{id}")
    ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart);
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCart(@PathVariable int id);
}
