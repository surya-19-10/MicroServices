package com.online.retail.customer.contoller;

import com.online.retail.customer.model.Customer;
import com.online.retail.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Value("${customer.message}")
    String message;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/greet")
    public String getMessage(){
        return message;
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return new ResponseEntity(customerService.addCustomer(customer), HttpStatus.CREATED);
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
        return new ResponseEntity(customerService.searchCustomer(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        return new ResponseEntity(customerService.updateCustomer(id, customer), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int id){
        return new ResponseEntity(customerService.deleteCustomer(id), HttpStatus.OK);
    }
}
