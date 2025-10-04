package com.glovendorproject.glovendor_backend_dev.service;


import com.glovendorproject.glovendor_backend_dev.entity.Analytics;
import com.glovendorproject.glovendor_backend_dev.repo.AnalyticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    // Record a new analytics entry
    public Analytics recordMetric(Analytics analytics) {
        return analyticsRepository.save(analytics);
    }

    // Retrieve all analytics records
    public List<Analytics> getAllMetrics() {
        return analyticsRepository.findAll();
    }

    // Retrieve analytics by metric name
    public List<Analytics> getMetricsByName(String metricName) {
        return analyticsRepository.findByMetricName(metricName);
    }

    // Retrieve a single analytics record by ID
    public Optional<Analytics> getMetricById(Long id) {
        return analyticsRepository.findById(id);
    }

    // Delete an analytics record
    public void deleteMetric(Long id) {
        analyticsRepository.deleteById(id);
    }
}
