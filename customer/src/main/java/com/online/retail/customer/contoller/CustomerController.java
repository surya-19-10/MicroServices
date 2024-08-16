package com.online.retail.customer.contoller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Value("${customer.message}")
    String message;
    @GetMapping("/greet")
    public String getMessage(){
        return message;
    }
}
