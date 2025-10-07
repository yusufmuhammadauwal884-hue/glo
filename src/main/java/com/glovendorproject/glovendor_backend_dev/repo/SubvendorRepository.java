package com.glovendorproject.glovendor_backend_dev.repo;
import com.glovendorproject.glovendor_backend_dev.entity.Subvendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SubvendorRepository extends JpaRepository<Subvendor, Long> {

    // Find by unique business name
    Optional<Subvendor> findByBusinessname(String businessname);

    // Find by email (used for authentication)
    Optional<Subvendor> findByEmail(String email);

    // Check if a subvendor exists with a given business name
    boolean existsByBusinessname(String businessname);
}
