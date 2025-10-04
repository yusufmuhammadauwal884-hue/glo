package com.glovendorproject.glovendor_backend_dev.repo;


import com.glovendorproject.glovendor_backend_dev.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionId(String transactionId);

    List<Transaction> findByMsisdn(String msisdn);

    List<Transaction> findByStatus(Transaction.Status status);

    boolean existsByTransactionId(String transactionId);
}
