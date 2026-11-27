package com.example.banking_transaction_system.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity 
@Table (name = "failed_transaction")
public class FailedTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "failed_id")
    private Long failedId;

    @Column (name = "transaction_id", nullable = false,unique=true)
    private Long transactionId;

    @Column(name = "error_category", nullable = false, length = 50)
    private String errorCategory;
   
    @Column(name = "reason", nullable = false, length = 500)
    private String reason;

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    public  FailedTransaction() {
    }
     public Long getFailedId() {
        return failedId;
    }
    public Long getTransactionId() {
        return transactionId;
    }
    public String getErrorCategory() {
        return errorCategory;
    }
    public void setFailedId(Long failedId) {
        this.failedId = failedId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    public void setErrorCategory(String errorCategory) {
        this.errorCategory = errorCategory;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getReason() {
        return reason;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
     @PrePersist 
    protected void onCreate() {
        if(createdAt == null) {
        createdAt = LocalDateTime.now();
        }
    }
}
