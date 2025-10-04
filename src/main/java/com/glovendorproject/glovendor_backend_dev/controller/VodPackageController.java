package com.glovendorproject.glovendor_backend_dev.controller;


import com.glovendorproject.glovendor_backend_dev.entity.VodPackage;
import com.glovendorproject.glovendor_backend_dev.service.VodPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vod-packages")
public class VodPackageController {

    @Autowired
    private VodPackageService vodPackageService;

    // Get all VOD packages
    @GetMapping("/")
    public List<VodPackage> getAllVodPackages() {
        return vodPackageService.getAllVodPackages();
    }

    // Get VOD package by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getVodPackageById(@PathVariable Long id) {
        return vodPackageService.getVodPackageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new VOD package
    @PostMapping("/add")
    public ResponseEntity<?> createVodPackage(@RequestBody VodPackage vodPackage) {
        try {
            VodPackage created = vodPackageService.createVodPackage(vodPackage);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Update VOD package
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVodPackage(@PathVariable Long id, @RequestBody VodPackage vodPackage) {
        try {
            VodPackage updated = vodPackageService.updateVodPackage(id, vodPackage);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete VOD package
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVodPackage(@PathVariable Long id) {
        try {
            vodPackageService.deleteVodPackage(id);
            return ResponseEntity.ok("VOD Package deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
