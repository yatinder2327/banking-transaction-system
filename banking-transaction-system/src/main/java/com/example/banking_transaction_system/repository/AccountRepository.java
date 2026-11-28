package com.example.banking_transaction_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.banking_transaction_system.entity.Account;

import jakarta.persistence.LockModeType;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Lock (LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a from Account a WHERE a.accountId = :accountId")
    Account findByAccountIdForUpdate(@Param ("accountId") Long accountId);
    boolean existsByAccountNumber(String accountNumber);
    List<Account> findByCustomer_CustomerId(Long customerId);


    
}
