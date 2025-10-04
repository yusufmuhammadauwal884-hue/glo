package com.glovendorproject.glovendor_backend_dev.repo;


import com.glovendorproject.glovendor_backend_dev.entity.DataPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DataPlanRepository extends JpaRepository<DataPlan, Long> {

    Optional<DataPlan> findByPlanId(String planId);

    boolean existsByPlanId(String planId);
}
