package com.online.retail.order.controller;

import com.online.retail.order.model.Order;
import com.online.retail.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Value("${order.message}")
    String message;
    @Autowired
    private OrderService orderService;
    @GetMapping("/greet")
    public String getMessage() {
        return message;
    }
    @PostMapping("/")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return new ResponseEntity(orderService.saveOrder(order), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        return new ResponseEntity<>(orderService.deleteOrder(id), HttpStatus.OK);
    }
}
