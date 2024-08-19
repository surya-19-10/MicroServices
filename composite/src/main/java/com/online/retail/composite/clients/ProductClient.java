package com.online.retail.composite.clients;

import com.online.retail.composite.core.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "products", url = "http://localhost:8081/products")
public interface ProductClient {
    @PostMapping("/")
    ResponseEntity<Product> addProduct(Product product);
    @GetMapping("/{id}")
    ResponseEntity<Product> searchProduct(@PathVariable int id);
}
