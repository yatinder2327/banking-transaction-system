package com.example.banking_transaction_system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateAccountRequest(
    @NotNull (message ="Customer Id is required")
    @Positive (message = "Customer Id must be a positive ")
    Long customerId
    

) {}