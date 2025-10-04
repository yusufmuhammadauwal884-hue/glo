package com.glovendorproject.glovendor_backend_dev.controller;


import com.glovendorproject.glovendor_backend_dev.entity.Admin;
import com.glovendorproject.glovendor_backend_dev.entity.Report;
import com.glovendorproject.glovendor_backend_dev.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Create a new report (upload or record)
    @PostMapping("/add")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.saveReport(report));
    }

    // Get all reports
    @GetMapping("/")
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    // Get reports by admin ID
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Report>> getReportsByAdmin(@PathVariable Long adminId) {
        // Create a mock admin object to query reports
        Admin admin = new Admin();
        admin.setId(adminId);
        return ResponseEntity.ok(reportService.getReportsByAdmin(admin));
    }

    // Get report by ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete report
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
