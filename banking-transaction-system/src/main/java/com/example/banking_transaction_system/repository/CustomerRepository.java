package com.example.banking_transaction_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_transaction_system.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String Phone);
    
} 
