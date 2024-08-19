package com.online.retail.composite.controller;

import com.online.retail.composite.core.Cart;
import com.online.retail.composite.core.Customer;
import com.online.retail.composite.core.LineItem;
import com.online.retail.composite.dto.CustomerOrderDto;
import com.online.retail.composite.dto.ProductDto;
import com.online.retail.composite.model.CustomerCart;
import com.online.retail.composite.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoppingservice")
public class CompositeController {
    @Autowired
    private CompositeService compositeService;
    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity(compositeService.addProduct(productDto), HttpStatus.CREATED);
    }
    @PostMapping("/customer")
    public ResponseEntity<CustomerCart> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity(compositeService.addCustomer(customer), HttpStatus.CREATED);
    }
    @PutMapping("/customer/{customerId}/cart")
    public ResponseEntity<Cart> addProductsToCart(@PathVariable(name = "customerId")int customerId, @RequestBody Cart cartItems) {
        return new ResponseEntity(compositeService.addProductsToCart(customerId, cartItems), HttpStatus.OK);
    }
    @PostMapping("/customer/{customerId}/order")
    public ResponseEntity<List<LineItem>> placeOrder(@PathVariable(name = "customerId")int customerId) {
        return new ResponseEntity(compositeService.placeOrder(customerId), HttpStatus.OK);
    }
    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<CustomerOrderDto> viewOrders(@PathVariable(name = "customerId")int customerId) {
        return new ResponseEntity(compositeService.viewOrders(customerId), HttpStatus.CREATED);
    }
}
