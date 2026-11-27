package com.example.banking_transaction_system.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity 
@Table(name = "customer")
public class Customer {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @Column(nullable = false,length = 100)
    private String name;
    @Column (nullable=false,unique=true,length=150)
    private String email;
    @Column(nullable=false,unique=true,length=15)
    private String phone;
    @Column (name="created_at",nullable = false)
    private LocalDateTime createdAt;
    @OneToMany (mappedBy = "customer")
    private List<Account> accounts = new ArrayList<>();
    
    public Long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Customer() {
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @PrePersist 
    protected void onCreate() {
        if(createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    
}
