package com.example.banking_transaction_system.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.banking_transaction_system.enums.TransactionStatus;
import com.example.banking_transaction_system.enums.TransactionType;

public record TransactionResponse(
    String transactionId,
    TransactionType type,
    BigDecimal amount,
    TransactionStatus status,
    Long accountId,
    BigDecimal updatedBalance,
    LocalDateTime createdAt,
    String message
) {}
