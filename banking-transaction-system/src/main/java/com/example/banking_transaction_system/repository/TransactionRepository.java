package com.example.banking_transaction_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_transaction_system.entity.BankTransaction;

public interface TransactionRepository extends JpaRepository<BankTransaction, Long> {
    boolean existsByTransactionId(String transactionId);
    
    
}
