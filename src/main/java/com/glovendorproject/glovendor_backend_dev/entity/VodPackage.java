package com.glovendorproject.glovendor_backend_dev.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vod_packages")
public class VodPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL = AUTO_INCREMENT
    private Long id;

    @NotBlank(message = "Package name cannot be empty")
    @Size(max = 100, message = "Package name cannot exceed 100 characters")
    @Column(name = "package_name", length = 100, nullable = false)
    private String packageName;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Digits(integer = 8, fraction = 2, message = "Invalid price format")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 day")
    @Column(name = "duration_days", nullable = false)
    private Integer durationDays;

    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
