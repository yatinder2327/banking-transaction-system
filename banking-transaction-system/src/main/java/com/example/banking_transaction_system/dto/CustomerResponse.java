package com.example.banking_transaction_system.dto;


import java.time.LocalDateTime;

public record CustomerResponse(
    Long customerId,
    String name,
    String email,
    String phone,
    LocalDateTime CreatedAt
)
{
    
}
