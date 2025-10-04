package com.glovendorproject.glovendor_backend_dev.repo;


import com.glovendorproject.glovendor_backend_dev.entity.VodPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VodPackageRepository extends JpaRepository<VodPackage, Long> {

    // Find a VOD package by name
    Optional<VodPackage> findByPackageName(String packageName);

    // Check if a package name already exists
    boolean existsByPackageName(String packageName);
}
