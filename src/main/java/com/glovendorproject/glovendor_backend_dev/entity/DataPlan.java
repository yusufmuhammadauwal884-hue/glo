package com.glovendorproject.glovendor_backend_dev.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "data_plans")
public class DataPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL in MySQL = AUTO_INCREMENT
    private Long id;

    @NotBlank(message = "Plan ID cannot be empty")
    @Size(max = 50, message = "Plan ID cannot exceed 50 characters")
    @Column(name = "plan_id", nullable = false, unique = true, length = 50)
    private String planId;

    @Size(max = 100, message = "Plan name cannot exceed 100 characters")
    @Column(length = 100)
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Invalid price format")
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Positive(message = "Validity days must be greater than 0")
    @Column(name = "validity_days")
    private Integer validityDays;

    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(Integer validityDays) {
        this.validityDays = validityDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
