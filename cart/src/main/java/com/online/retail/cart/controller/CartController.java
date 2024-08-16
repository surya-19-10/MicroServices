package com.online.retail.cart.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Value("${cart.message}")
    String message;
    @GetMapping("/greet")
    public String getMessage() {
        return message;
    }
}
