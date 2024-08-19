package com.online.retail.cart.controller;

import com.online.retail.cart.model.Cart;
import com.online.retail.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Value("${cart.message}")
    String message;
    @Autowired
    private CartService cartService;
    @GetMapping("/greet")
    public String getMessage() {
        return message;
    }
    @PostMapping("/")
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
        return new ResponseEntity(cartService.saveCart(cart), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable int id) {
        return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart) {
        return new ResponseEntity(cartService.updateCart(id, cart), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable int id) {
        return new ResponseEntity<>(cartService.deleteCart(id), HttpStatus.OK);
    }
}
