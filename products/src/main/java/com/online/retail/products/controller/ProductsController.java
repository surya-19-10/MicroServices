package com.online.retail.products.controller;

import com.online.retail.products.model.Product;
import com.online.retail.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Value("${products.message}")
    String message;
    @Autowired
    private ProductService productService;
    @GetMapping("/greet")
    public String getMessage() {
        return message;
    }
    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity(productService.addProduct(product), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> searchProduct(@PathVariable int id){
        return new ResponseEntity(productService.searchProduct(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        return new ResponseEntity(productService.updateProduct(id, product), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){
        return new ResponseEntity(productService.deleteProduct(id), HttpStatus.OK);
    }
}
