package com.rewards.calculation.controller;

import com.rewards.calculation.domain.Customer;
import com.rewards.calculation.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return this.customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Integer customerId){
        Optional<Customer> customer = this.customerService.getCustomer(customerId);
        if(customer.isPresent()){
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Customer>  createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(this.customerService.saveCustomer(customer), HttpStatus.CREATED);
    }
}
