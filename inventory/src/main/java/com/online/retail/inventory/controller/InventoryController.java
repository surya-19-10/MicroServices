package com.online.retail.inventory.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Value("${inventory.message}")
    String message;
    @GetMapping("/greet")
    public String getMessage(){
        return message;
    }
}
