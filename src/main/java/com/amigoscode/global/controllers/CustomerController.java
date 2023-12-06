package com.amigoscode.global.controllers;

import com.amigoscode.global.dto.CustomerDto;

import com.amigoscode.global.entities.Customer;

import com.amigoscode.global.exceptions.CustomerNotFoundException;

import com.amigoscode.global.services.CustomerService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public List<Customer> findAllCustomers() {

        return customerService.findAllCustomers();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Integer id)

        throws CustomerNotFoundException {

        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(

        @RequestBody @Valid CustomerDto customerDto

    ) throws Exception {

        return new ResponseEntity<>(

            customerService.createCustomer(customerDto),

            HttpStatus.CREATED

        );

    }

    @PutMapping("")
    public ResponseEntity<Customer> updateCustomerById(

        @RequestBody @Valid CustomerDto customerDto

    ) {

        return new ResponseEntity<>(

            customerService.updateCustomerById(customerDto),

            HttpStatus.ACCEPTED

        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable int id) {

        customerService.deleteCustomerById(id);

        return ResponseEntity.noContent().build();

    }

}
