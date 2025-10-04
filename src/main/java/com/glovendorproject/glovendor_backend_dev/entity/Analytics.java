package com.glovendorproject.glovendor_backend_dev.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL = AUTO_INCREMENT
    private Long id;

    @NotBlank(message = "Metric name cannot be empty")
    @Column(name = "metric_name", length = 100, nullable = false)
    private String metricName;

    @NotNull(message = "Value cannot be null")
    @Digits(integer = 15, fraction = 2, message = "Value must be a valid decimal number")
    @Column(name = "value", precision = 15, scale = 2, nullable = false)
    private BigDecimal value;

    @Column(name = "recorded_at", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime recordedAt;

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
}
