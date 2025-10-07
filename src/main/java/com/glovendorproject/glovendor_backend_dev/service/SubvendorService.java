package com.glovendorproject.glovendor_backend_dev.service;
import com.glovendorproject.glovendor_backend_dev.entity.Subvendor;
import com.glovendorproject.glovendor_backend_dev.repo.SubvendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubvendorService {

    private final SubvendorRepository subvendorRepository;

    @Autowired
    public SubvendorService(SubvendorRepository subvendorRepository) {
        this.subvendorRepository = subvendorRepository;
    }

    // Create a new subvendor (with duplicate check)
    public Subvendor createSubvendor(Subvendor subvendor) {
        if (subvendorRepository.existsByBusinessname(subvendor.getBusinessname())) {
            throw new RuntimeException("Business name already exists: " + subvendor.getBusinessname());
        }
        // Hash password if provided
        if (subvendor.getPassword() != null && !subvendor.getPassword().isEmpty()) {
            org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            subvendor.setPasswordHash(encoder.encode(subvendor.getPassword()));
        }

        return subvendorRepository.save(subvendor);
    }

    // Get all subvendors
    public List<Subvendor> getAllSubvendors() {
        return subvendorRepository.findAll();
    }

    // Get one subvendor by ID
    public Optional<Subvendor> getSubvendorById(Long id) {
        return subvendorRepository.findById(id);
    }

    // Get one subvendor by businessname
    public Optional<Subvendor> getSubvendorByBusinessname(String businessname) {
        return subvendorRepository.findByBusinessname(businessname);
    }

    // Find by email (used for authentication)
    public Optional<Subvendor> findByEmail(String email) {
        return subvendorRepository.findByEmail(email);
    }

    // Update subvendor
    public Subvendor updateSubvendor(Long id, Subvendor updatedSubvendor) {
        return subvendorRepository.findById(id).map(subvendor -> {
            subvendor.setBusinessname(updatedSubvendor.getBusinessname());
            subvendor.setContactPerson(updatedSubvendor.getContactPerson());
            subvendor.setEmail(updatedSubvendor.getEmail());
            subvendor.setPhone(updatedSubvendor.getPhone());
            subvendor.setAddress(updatedSubvendor.getAddress());
            return subvendorRepository.save(subvendor);
        }).orElseThrow(() -> new RuntimeException("Subvendor not found with id: " + id));
    }

    // Delete subvendor
    public void deleteSubvendor(Long id) {
        subvendorRepository.deleteById(id);
    }
}
