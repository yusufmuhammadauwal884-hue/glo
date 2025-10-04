package com.glovendorproject.glovendor_backend_dev.service;


import com.glovendorproject.glovendor_backend_dev.entity.DataPlan;
import com.glovendorproject.glovendor_backend_dev.repo.DataPlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DataPlanService {

    private final DataPlanRepository dataPlanRepository;

    public DataPlanService(DataPlanRepository dataPlanRepository) {
        this.dataPlanRepository = dataPlanRepository;
    }

    // ✅ Create new data plan (with duplicate check)
    @Transactional
    public DataPlan createDataPlan(DataPlan dataPlan) {
        if (dataPlanRepository.existsByPlanId(dataPlan.getPlanId())) {
            throw new IllegalArgumentException("Plan ID already exists: " + dataPlan.getPlanId());
        }
        return dataPlanRepository.save(dataPlan);
    }

    // ✅ Get all data plans
    public List<DataPlan> getAllDataPlans() {
        return dataPlanRepository.findAll();
    }

    // ✅ Get data plan by ID
    public Optional<DataPlan> getDataPlanById(Long id) {
        return dataPlanRepository.findById(id);
    }

    // ✅ Get data plan by plan ID
    public Optional<DataPlan> getDataPlanByPlanId(String planId) {
        return dataPlanRepository.findByPlanId(planId);
    }

    // ✅ Update data plan
    @Transactional
    public DataPlan updateDataPlan(Long id, DataPlan updatedPlan) {
        return dataPlanRepository.findById(id).map(plan -> {
            plan.setName(updatedPlan.getName());
            plan.setPrice(updatedPlan.getPrice());
            plan.setValidityDays(updatedPlan.getValidityDays());
            return dataPlanRepository.save(plan);
        }).orElseThrow(() -> new IllegalArgumentException("Data plan not found with id: " + id));
    }

    // ✅ Delete data plan
    @Transactional
    public void deleteDataPlan(Long id) {
        if (!dataPlanRepository.existsById(id)) {
            throw new IllegalArgumentException("Data plan not found with id: " + id);
        }
        dataPlanRepository.deleteById(id);
    }
}
