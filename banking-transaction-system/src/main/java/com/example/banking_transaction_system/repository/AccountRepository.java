package com.example.banking_transaction_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_transaction_system.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(String accountNumber);
    List<Account> findByCustomer_CustomerId(Long customerId);

    
}
