package com.example.banking_transaction_system.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest(
    @NotNull (message = "Account Id is required")
    @Positive (message = "Account Id must be a positive")
    Long accountId,
    @NotNull (message="Amount is required")
    @Positive (message = "Amount must be greater than zero")
    BigDecimal amount,
    @NotNull (message = "Transaction Id is required")
    String transactionId
) {}
