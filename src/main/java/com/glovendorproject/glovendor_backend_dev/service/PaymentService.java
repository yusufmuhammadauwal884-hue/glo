package com.glovendorproject.glovendor_backend_dev.service;


import com.glovendorproject.glovendor_backend_dev.entity.Payment;
import com.glovendorproject.glovendor_backend_dev.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get payment by ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // Get payment by transaction ID
    public Optional<Payment> getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }

    // Create new payment record
    public Payment createPayment(Payment payment) {
        if (paymentRepository.existsByPaymentReference(payment.getPaymentReference())) {
            throw new IllegalArgumentException("Payment reference already exists");
        }

        // Automatically set paidAt time if not provided
        if (payment.getPaidAt() == null) {
            payment.setPaidAt(LocalDateTime.now());
        }

        payment.setConfirmed(false);
        return paymentRepository.save(payment);
    }

    // Confirm a payment
    public Payment confirmPayment(String paymentReference) {
        Payment payment = paymentRepository.findByPaymentReference(paymentReference)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.setConfirmed(true);
        return paymentRepository.save(payment);
    }

    // Delete payment record
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new IllegalArgumentException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }
}
