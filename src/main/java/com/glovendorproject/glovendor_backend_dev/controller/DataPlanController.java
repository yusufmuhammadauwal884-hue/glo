package com.glovendorproject.glovendor_backend_dev.controller;


import com.glovendorproject.glovendor_backend_dev.entity.DataPlan;
import com.glovendorproject.glovendor_backend_dev.service.DataPlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-plans")
public class DataPlanController {

    private final DataPlanService dataPlanService;

    public DataPlanController(DataPlanService dataPlanService) {
        this.dataPlanService = dataPlanService;
    }

    // ✅ Create new data plan
    @PostMapping("/add")
    public ResponseEntity<DataPlan> createDataPlan(@Valid @RequestBody DataPlan dataPlan) {
        DataPlan saved = dataPlanService.createDataPlan(dataPlan);
        return ResponseEntity.ok(saved);
    }

    // ✅ Get all data plans
    @GetMapping("/")
    public ResponseEntity<List<DataPlan>> getAllDataPlans() {
        return ResponseEntity.ok(dataPlanService.getAllDataPlans());
    }

    // ✅ Get data plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<DataPlan> getDataPlanById(@PathVariable Long id) {
        return dataPlanService.getDataPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get data plan by plan ID
    @GetMapping("/plan/{planId}")
    public ResponseEntity<DataPlan> getDataPlanByPlanId(@PathVariable String planId) {
        return dataPlanService.getDataPlanByPlanId(planId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update data plan
    @PutMapping("/{id}")
    public ResponseEntity<DataPlan> updateDataPlan(@PathVariable Long id, @Valid @RequestBody DataPlan dataPlan) {
        try {
            DataPlan updated = dataPlanService.updateDataPlan(id, dataPlan);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Delete data plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataPlan(@PathVariable Long id) {
        try {
            dataPlanService.deleteDataPlan(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
