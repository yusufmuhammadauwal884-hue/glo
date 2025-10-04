package com.glovendorproject.glovendor_backend_dev.service;


import com.glovendorproject.glovendor_backend_dev.entity.Admin;
import com.glovendorproject.glovendor_backend_dev.repo.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // ✅ Create admin (with duplicate check)
    @Transactional
    public Admin createAdmin(Admin admin) {
        if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + admin.getUsername());
        }
        return adminRepository.save(admin);
    }

    // ✅ Get all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // ✅ Get admin by ID
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // ✅ Get admin by username
    public Optional<Admin> getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    // ✅ Update admin (role or password)
    @Transactional
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        return adminRepository.findById(id).map(admin -> {
            admin.setRole(updatedAdmin.getRole());
            admin.setPasswordHash(updatedAdmin.getPasswordHash());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new IllegalArgumentException("Admin not found with id: " + id));
    }

    // ✅ Delete admin
    @Transactional
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new IllegalArgumentException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }
}
