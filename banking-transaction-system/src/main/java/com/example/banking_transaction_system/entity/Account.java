package com.example.banking_transaction_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.banking_transaction_system.enums.AccountStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity 
@Table (name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;  
    @Column(name="account_number", nullable = false, unique = true,length = 20)
    private String accountNumber;
    @Column(nullable = false,precision = 19, scale = 2)
    private BigDecimal balance=BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status=AccountStatus.ACTIVE;
    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Account() {
        
    }

    @PrePersist 
    protected void onCreate() {
        if(createdAt == null) {
        createdAt = LocalDateTime.now();
        }

        if(balance == null) {
            balance = BigDecimal.ZERO;
        }
        if(status == null) {
            status = AccountStatus.ACTIVE;
        }
    }

}
