package com.online.retail.composite.clients;

import com.online.retail.composite.core.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cart")
public interface CartClient {
    @PostMapping("/cart/")
    ResponseEntity<Cart> saveCart(Cart cart);
    @GetMapping("/cart/{id}")
    ResponseEntity<Cart> getCart(@PathVariable int id);
    @PutMapping("/cart/{id}")
    ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart);
    @DeleteMapping("/cart/{id}")
    ResponseEntity<String> deleteCart(@PathVariable int id);
}
