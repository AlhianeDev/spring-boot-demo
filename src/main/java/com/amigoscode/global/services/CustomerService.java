package com.amigoscode.global.services;

import com.amigoscode.global.Repositories.CustomerRepo;

import com.amigoscode.global.dto.CustomerDto;

import com.amigoscode.global.entities.Customer;

import com.amigoscode.global.exceptions.CustomerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {

        this.customerRepo = customerRepo;

    }

    public List<Customer> findAllCustomers() {

        return customerRepo.findAll();

    }

    public Customer findCustomerById(Integer id) throws CustomerNotFoundException {

        if (customerRepo.findById(id).isPresent()) {

            return customerRepo.findById(id).get();

        } else {

            throw new CustomerNotFoundException("Customer With Id " + id + " Is Not Found!");

        }

    }

    public Customer createCustomer(CustomerDto customerDto) throws Exception {

        Customer customer = Customer.builder()

            .id(customerDto.getId())

            .name(customerDto.getName())

            .email(customerDto.getEmail())

            .age(customerDto.getAge())

            .gender(customerDto.getGender())

            .nationality(customerDto.getNationality())

            .mobile(customerDto.getMobile())

        .build();

        Optional<Customer> gotCustomer = customerRepo.findByEmail(customerDto.getEmail());

        if (gotCustomer.isPresent()) {

            throw new Exception("Customer Already Exists!");

        }

        return customerRepo.save(customer);

    }

    public Customer updateCustomerById(CustomerDto customerDto) {

        Customer customer = Customer.builder()

            .id(customerDto.getId())

            .name(customerDto.getName())

            .email(customerDto.getEmail())

            .age(customerDto.getAge())

            .gender(customerDto.getGender())

            .nationality(customerDto.getNationality())

            .mobile(customerDto.getMobile())

        .build();

        return customerRepo.save(customer);

    }

    public void deleteCustomerById(int id) {

        customerRepo.deleteById(id);

    }

}
