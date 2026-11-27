package com.example.banking_transaction_system.dto;

import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(
    @NotNull Long customerId
    

) {}