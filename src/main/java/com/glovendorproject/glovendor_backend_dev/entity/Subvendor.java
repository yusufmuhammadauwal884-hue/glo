package com.glovendorproject.glovendor_backend_dev.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "subvendors")
public class Subvendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "businessname", nullable = false, length = 100, unique = true)
    private String businessname;

    @Column(name = "contact_person", length = 100, nullable = false)
    private String contactPerson;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email cannot be longer than 100 characters")
    @Column( nullable = false, unique = true)
    private String email;

    @NotBlank(message = "PhoneNo cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "PhoneNo must be exactly 11 digits")
    @Column(nullable = false, unique = true, length = 11)
    private String phone;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;

    @JsonIgnore
    @Column(name = "password_hash", columnDefinition = "TEXT")
    private String passwordHash;

    // Plain-text password coming from signup requests - not persisted
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private String password;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // Constructors
    public Subvendor() {}

    public Subvendor(String businessname, String contactPerson, String email, String phone, String address) {
        this.businessname = businessname;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Ensure created_at gets auto-populated on insert
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
