package com.example.banking_transaction_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_transaction_system.dto.TransactionRequest;
import com.example.banking_transaction_system.dto.TransactionResponse;
import com.example.banking_transaction_system.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse>deposit(@Valid @RequestBody TransactionRequest request){
        return ResponseEntity.ok(transactionService.deposit(request));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionResponse>withdraw(@Valid @RequestBody TransactionRequest request){
        return ResponseEntity.ok(transactionService.withdrawal(request));
    }

    
}
