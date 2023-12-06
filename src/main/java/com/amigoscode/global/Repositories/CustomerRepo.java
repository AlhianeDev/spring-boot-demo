package com.amigoscode.global.Repositories;

import com.amigoscode.global.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    public boolean existsByEmail(String email);

    public Optional<Customer> findByEmail(String email);

    @Query("select c from Customer c where c.name = ?1")
    public  Customer findByJpql(String name);

}
