package com.glovendorproject.glovendor_backend_dev.repo;



import com.glovendorproject.glovendor_backend_dev.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customer by msisdn
    Optional<Customer> findByMsisdn(String msisdn);

    // Find customer by email
    Optional<Customer> findByEmail(String email);

    // Check if msisdn exists (useful for validation before saving)
    boolean existsByMsisdn(String msisdn);

    // Check if email exists
    boolean existsByEmail(String email);
}
