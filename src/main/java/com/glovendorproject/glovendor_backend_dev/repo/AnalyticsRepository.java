package com.glovendorproject.glovendor_backend_dev.repo;


import com.glovendorproject.glovendor_backend_dev.entity.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {

    // Find analytics by metric name (e.g., "TOTAL_SALES")
    List<Analytics> findByMetricName(String metricName);
}
