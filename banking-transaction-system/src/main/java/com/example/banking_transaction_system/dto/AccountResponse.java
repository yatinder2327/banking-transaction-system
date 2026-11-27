package com.example.banking_transaction_system.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.banking_transaction_system.enums.AccountStatus;

public record AccountResponse(
    Long accountId,
    Long customerId,
    String accountNumber,
    BigDecimal balance,
    AccountStatus status,
    LocalDateTime createdAt
) {
    
}
