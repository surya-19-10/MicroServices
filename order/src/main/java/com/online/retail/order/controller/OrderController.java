package com.online.retail.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Value("${order.message}")
    String message;
    @GetMapping("/greet")
    public String getMessage() {
        return message;
    }
}
