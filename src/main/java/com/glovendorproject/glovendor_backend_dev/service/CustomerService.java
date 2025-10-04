package com.glovendorproject.glovendor_backend_dev.service;


import com.glovendorproject.glovendor_backend_dev.entity.Customer;
import com.glovendorproject.glovendor_backend_dev.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // ✅ Create a new customer with validation
    @Transactional
    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByMsisdn(customer.getMsisdn())) {
            throw new IllegalArgumentException("MSISDN already exists: " + customer.getMsisdn());
        }
        if (customer.getEmail() != null && customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + customer.getEmail());
        }
        return customerRepository.save(customer);
    }

    // ✅ Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // ✅ Find customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // ✅ Find customer by MSISDN
    public Optional<Customer> getCustomerByMsisdn(String msisdn) {
        return customerRepository.findByMsisdn(msisdn);
    }

    // ✅ Update customer
    @Transactional
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(customer -> {
            customer.setFullName(updatedCustomer.getFullName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPasswordHash(updatedCustomer.getPasswordHash());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
    }

    // ✅ Delete customer
    @Transactional
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
