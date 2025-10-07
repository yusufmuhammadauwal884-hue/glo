package com.glovendorproject.glovendor_backend_dev.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL = AUTO_INCREMENT
    private Long id;

    @NotBlank(message = "Transaction ID cannot be empty")
    @Size(max = 50, message = "Transaction ID cannot exceed 50 characters")
    @Column(name = "transaction_id", nullable = false, unique = true, length = 50)
    private String transactionId;

    @NotBlank(message = "MSISDN cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "MSISDN must be between 11 digits")
    @Column(nullable = false, length = 11)
    private String msisdn;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", length = 20)
    private ServiceType serviceType;

    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Invalid amount format")
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "data_plan_id", referencedColumnName = "id")
    private DataPlan dataPlan; // Foreign key reference to data_plans table

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @Column(name = "response_message", columnDefinition = "TEXT")
    private String responseMessage;

    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // --- ENUMS ---
    public enum ServiceType {
        TOPUP,
        DATA,
        VOD
    }

    public enum Status {
        SUCCESS,
        FAILED,
        PENDING
    }

    // --- Getters & Setters ---

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

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DataPlan getDataPlan() {
        return dataPlan;
    }

    public void setDataPlan(DataPlan dataPlan) {
        this.dataPlan = dataPlan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
