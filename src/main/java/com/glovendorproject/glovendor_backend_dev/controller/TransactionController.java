package com.glovendorproject.glovendor_backend_dev.controller;


import com.glovendorproject.glovendor_backend_dev.entity.Transaction;
import com.glovendorproject.glovendor_backend_dev.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-transaction-id/{transactionId}")
    public ResponseEntity<Transaction> getByTransactionId(@PathVariable String transactionId) {
        return transactionService.getTransactionByTransactionId(transactionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-msisdn/{msisdn}")
    public List<Transaction> getByMsisdn(@PathVariable String msisdn) {
        return transactionService.getTransactionsByMsisdn(msisdn);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction saved = transactionService.createTransaction(transaction);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{transactionId}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable String transactionId,
            @RequestParam Transaction.Status status,
            @RequestParam(required = false) String responseMessage) {
        try {
            Transaction updated = transactionService.updateTransactionStatus(transactionId, status, responseMessage);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
}
