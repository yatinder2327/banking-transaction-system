package com.example.banking_transaction_system.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.banking_transaction_system.dto.AccountResponse;
import com.example.banking_transaction_system.dto.CreateAccountRequest;
import com.example.banking_transaction_system.entity.Account;
import com.example.banking_transaction_system.entity.Customer;
import com.example.banking_transaction_system.enums.AccountStatus;
import com.example.banking_transaction_system.repository.AccountRepository;
import com.example.banking_transaction_system.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service 
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(
        AccountRepository accountRepository, CustomerRepository customerRepository
    ) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            Account account = new Account();
            account.setCustomer(customer);
            account.setAccountNumber(generateAccountNumber());
            account.setBalance(BigDecimal.ZERO);
            account.setStatus(AccountStatus.ACTIVE);
            Account savedAccount = accountRepository.save(account);
            return mapToResponse(savedAccount);
            
    }

    private String generateAccountNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();

    }
    
    @Transactional (readOnly = true)
    public AccountResponse getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
            return mapToResponse(account);
    }
    
    @Transactional (readOnly = true)
    
  
}
