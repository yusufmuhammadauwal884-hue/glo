package com.glovendorproject.glovendor_backend_dev.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL = AUTO_INCREMENT
    private Long id;

    @NotBlank(message = "Transaction ID cannot be empty")
    @Size(max = 50, message = "Transaction ID cannot exceed 50 characters")
    @Column(name = "transaction_id", length = 50, nullable = false)
    private String transactionId;

    @NotBlank(message = "Payment method cannot be empty")
    @Size(max = 50, message = "Payment method cannot exceed 50 characters")
    @Column(name = "payment_method", length = 50, nullable = false)
    private String paymentMethod;

    @NotBlank(message = "Payment reference cannot be empty")
    @Size(max = 100, message = "Payment reference cannot exceed 100 characters")
    @Column(name = "payment_reference", length = 100, nullable = false, unique = true)
    private String paymentReference;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean confirmed = false;

    // Optional relationship (if you want to link to Transaction entity)
    // @ManyToOne
    // @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id", insertable = false, updatable = false)
    // private Transaction transaction;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
