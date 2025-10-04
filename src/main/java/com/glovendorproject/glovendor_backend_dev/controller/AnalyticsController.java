package com.glovendorproject.glovendor_backend_dev.controller;


import com.glovendorproject.glovendor_backend_dev.entity.Analytics;
import com.glovendorproject.glovendor_backend_dev.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // Create a new analytics record
    @PostMapping("/add")
    public ResponseEntity<Analytics> recordMetric(@RequestBody Analytics analytics) {
        return ResponseEntity.ok(analyticsService.recordMetric(analytics));
    }

    // Get all analytics
    @GetMapping("/")
    public ResponseEntity<List<Analytics>> getAllMetrics() {
        return ResponseEntity.ok(analyticsService.getAllMetrics());
    }

    // Get analytics by metric name
    @GetMapping("/name/{metricName}")
    public ResponseEntity<List<Analytics>> getMetricsByName(@PathVariable String metricName) {
        return ResponseEntity.ok(analyticsService.getMetricsByName(metricName));
    }

    // Get analytics by ID
    @GetMapping("/{id}")
    public ResponseEntity<Analytics> getMetricById(@PathVariable Long id) {
        return analyticsService.getMetricById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete analytics record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetric(@PathVariable Long id) {
        analyticsService.deleteMetric(id);
        return ResponseEntity.noContent().build();
    }
}
