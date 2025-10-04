package com.glovendorproject.glovendor_backend_dev.controller;

import com.glovendorproject.glovendor_backend_dev.entity.Subvendor;
import com.glovendorproject.glovendor_backend_dev.service.SubvendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subvendors")
public class SubvendorController {

    private final SubvendorService subvendorService;

    @Autowired
    public SubvendorController(SubvendorService subvendorService) {
        this.subvendorService = subvendorService;
    }

    // Create
    @PostMapping("/add")
    public ResponseEntity<Subvendor> createSubvendor(@RequestBody Subvendor subvendor) {
        return ResponseEntity.ok(subvendorService.createSubvendor(subvendor));
    }

    // Read all
    @GetMapping("/")
    public ResponseEntity<List<Subvendor>> getAllSubvendors() {
        return ResponseEntity.ok(subvendorService.getAllSubvendors());
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Subvendor> getSubvendorById(@PathVariable Long id) {
        return subvendorService.getSubvendorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Read by businessname
    @GetMapping("/business/{businessname}")
    public ResponseEntity<Subvendor> getByBusinessname(@PathVariable String businessname) {
        return subvendorService.getSubvendorByBusinessname(businessname)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Subvendor> updateSubvendor(@PathVariable Long id, @RequestBody Subvendor subvendor) {
        return ResponseEntity.ok(subvendorService.updateSubvendor(id, subvendor));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubvendor(@PathVariable Long id) {
        subvendorService.deleteSubvendor(id);
        return ResponseEntity.noContent().build();
    }
}
