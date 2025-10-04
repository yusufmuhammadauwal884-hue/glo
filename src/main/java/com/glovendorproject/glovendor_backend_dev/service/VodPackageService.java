package com.glovendorproject.glovendor_backend_dev.service;


import com.glovendorproject.glovendor_backend_dev.entity.VodPackage;
import com.glovendorproject.glovendor_backend_dev.repo.VodPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VodPackageService {

    @Autowired
    private VodPackageRepository vodPackageRepository;

    // Get all VOD packages
    public List<VodPackage> getAllVodPackages() {
        return vodPackageRepository.findAll();
    }

    // Get a specific VOD package by ID
    public Optional<VodPackage> getVodPackageById(Long id) {
        return vodPackageRepository.findById(id);
    }

    // Create a new VOD package
    public VodPackage createVodPackage(VodPackage vodPackage) {
        if (vodPackageRepository.existsByPackageName(vodPackage.getPackageName())) {
            throw new IllegalArgumentException("Package name already exists");
        }
        return vodPackageRepository.save(vodPackage);
    }

    // Update an existing VOD package
    public VodPackage updateVodPackage(Long id, VodPackage updatedVodPackage) {
        VodPackage existingPackage = vodPackageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("VOD Package not found"));

        existingPackage.setPackageName(updatedVodPackage.getPackageName());
        existingPackage.setPrice(updatedVodPackage.getPrice());
        existingPackage.setDurationDays(updatedVodPackage.getDurationDays());

        return vodPackageRepository.save(existingPackage);
    }

    // Delete a VOD package by ID
    public void deleteVodPackage(Long id) {
        if (!vodPackageRepository.existsById(id)) {
            throw new IllegalArgumentException("VOD Package not found");
        }
        vodPackageRepository.deleteById(id);
    }
}
