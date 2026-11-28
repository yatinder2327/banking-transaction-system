package com.example.banking_transaction_system.service;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.banking_transaction_system.dto.TransactionRequest;
import com.example.banking_transaction_system.dto.TransactionResponse;
import com.example.banking_transaction_system.entity.Account;
import com.example.banking_transaction_system.entity.BankTransaction;
import com.example.banking_transaction_system.enums.AccountStatus;
import com.example.banking_transaction_system.enums.TransactionStatus;
import com.example.banking_transaction_system.enums.TransactionType;
import com.example.banking_transaction_system.repository.AccountRepository;
import com.example.banking_transaction_system.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service 
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionResponse deposit(TransactionRequest request){
        validateDuplicateTransaction(request.transactionId());
        Account account = getLockedAccount(request.accountId());
        BankTransaction transaction = createTransaction(request,TransactionType.DEPOSIT);
        transaction.setDestinationAccount(account);

        if(account.getStatus() != AccountStatus.ACTIVE){
            transaction.setStatus(TransactionStatus.REJECTED);   
            transactionRepository.save(transaction);
            return createResponse(transaction, account,"Deposit rejected: Account is not active");
        }
        BigDecimal updatedBalance = account.getBalance().add(request.amount());
        account.setBalance(updatedBalance);
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        return createResponse(transaction,account,"Deposit successfully");


    }

    @Transactional 
    public TransactionResponse withdrawal(TransactionRequest request){
        validateDuplicateTransaction(request.transactionId());
        Account account = getLockedAccount(request.accountId());
        BankTransaction transaction = createTransaction(request,TransactionType.WITHDRAWAL);
        transaction.setSourceAccount(account);

        if(account.getStatus() != AccountStatus.ACTIVE){
        transaction.setStatus(TransactionStatus.REJECTED);
        transactionRepository.save(transaction);
        return createResponse(transaction, account, "Withdrawal rejected: Account is not active");
        }

        if (account.getBalance().compareTo(request.amount()) < 0) {
        transaction.setStatus(TransactionStatus.REJECTED);
        transactionRepository.save(transaction);
        return createResponse(transaction, account, "Withdrawal rejected: Insufficient balance");
        }
        BigDecimal updatedBalance = account.getBalance().subtract(request.amount());
        account.setBalance(updatedBalance);
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        return createResponse(transaction, account, "Withdrawal successful");
    }
    
    private TransactionResponse createResponse(BankTransaction transaction, Account account, String message) {
        return new TransactionResponse(
        transaction.getTransactionId(),
        transaction.getType(),
        transaction.getAmount(),
        transaction.getStatus(),
        account.getAccountId(),
        account.getBalance(),
        transaction.getCreatedAt(),
        message
        );

    }

    private BankTransaction createTransaction(TransactionRequest request, TransactionType type) {
        BankTransaction transaction = new BankTransaction();
        transaction.setTransactionId(request.transactionId());
        transaction.setType(type);
        transaction.setAmount(request.amount());
        return transaction;
        
    }

    private Account getLockedAccount(Long accountId){
        Account account=accountRepository.findByAccountIdForUpdate(accountId);
        if(account == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found");
        }
        return account;
    }

    private void validateDuplicateTransaction(String transactionId) {
        if(transactionRepository.existsByTransactionId(transactionId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Duplicate transaction id");
        }
    }
}
