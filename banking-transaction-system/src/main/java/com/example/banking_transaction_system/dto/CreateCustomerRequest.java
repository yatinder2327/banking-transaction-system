package com.example.banking_transaction_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest(
    @NotBlank (message = "Customer name is required")
    @Size(max=100,message="Name cannot exceed 100 characters")
    String name,
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email ")
    @Size(max=150)
    String email,
    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^[0-9]{10,15}$", message = "Phone must contain 10 and 15 digits"

    )
    String phone
) {}
