package com.techm.mobileprepaidrechargesystem.service;

import com.techm.mobileprepaidrechargesystem.model.Transaction;
import com.techm.mobileprepaidrechargesystem.model.User.AccountStatus;
import com.techm.mobileprepaidrechargesystem.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserUserId(userId);
    }

    // Add a new transaction
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    public long getTotalTransactions() {
        return transactionRepository.getTotalTransactions();
    }

    public long getSuccessfulTransactions() {
        return transactionRepository.getSuccessfulTransactions();
    }

    public long getFailedTransactions() {
        return transactionRepository.getFailedTransactions();
    }

    public double getTotalRevenue() {
        return transactionRepository.getTotalRevenue();
    }


	public Optional<Transaction> getTransactionsById(Long transactionId) {
		
		return transactionRepository.findById(transactionId);
	}
}
