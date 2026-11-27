package com.example.banking_transaction_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.banking_transaction_system.enums.TransactionStatus;
import com.example.banking_transaction_system.enums.TransactionType;
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
@Table (name = "transactions")
public class BankTransaction {
    public Long getId() {
        return id;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public TransactionType getType() {
        return type;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public Account getSourceAccount() {
        return sourceAccount;
    }
    public Account getDestinationAccount() {
        return destinationAccount;
    }
    public TransactionStatus getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "transaction_id", nullable = false, unique = true, length = 64)
    private String transactionId;
    @Enumerated(EnumType.STRING)
    @Column( nullable = false,length = 20)
    private TransactionType type;
    @Column (nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn (name = "Source_Account_Id")
    private Account sourceAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "Destination_Account_Id")
    private Account destinationAccount;
    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 20)
    private TransactionStatus status;
    @Column (name="created_at", nullable = false)
    private LocalDateTime createdAt;
    public BankTransaction() {
    }
    @PrePersist 
    protected void onCreate() {
        if(createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
