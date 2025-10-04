package com.glovendorproject.glovendor_backend_dev.service;


import com.glovendorproject.glovendor_backend_dev.entity.Transaction;
import com.glovendorproject.glovendor_backend_dev.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Optional<Transaction> getTransactionByTransactionId(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    public List<Transaction> getTransactionsByMsisdn(String msisdn) {
        return transactionRepository.findByMsisdn(msisdn);
    }

    public Transaction createTransaction(Transaction transaction) {
        if (transactionRepository.existsByTransactionId(transaction.getTransactionId())) {
            throw new IllegalArgumentException("Transaction ID already exists");
        }
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransactionStatus(String transactionId, Transaction.Status newStatus, String responseMessage) {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        transaction.setStatus(newStatus);
        transaction.setResponseMessage(responseMessage);

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
