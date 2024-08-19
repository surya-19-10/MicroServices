package com.online.retail.composite.clients;

import com.online.retail.composite.core.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order", url="http://localhost:8084/order")
public interface OrderClient {
    @PostMapping("/")
    ResponseEntity<Order> addOrder(@RequestBody Order order);
    @GetMapping("/{id}")
    ResponseEntity<Order> getOrder(@PathVariable int id);
}
