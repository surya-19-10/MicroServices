package com.online.retail.composite.clients;

import com.online.retail.composite.core.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "products")
public interface ProductClient {
    @PostMapping("/products/")
    ResponseEntity<Product> addProduct(Product product);
    @GetMapping("/products/{id}")
    ResponseEntity<Product> searchProduct(@PathVariable int id);
}
