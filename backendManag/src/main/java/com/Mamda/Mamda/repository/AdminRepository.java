package com.Mamda.Mamda.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.Mamda.Mamda.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);

    Optional<Admin> findByUsername(String username);

    Boolean existsByEmail(String email);

    default Admin findByEmailOrThrow(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
    }
}
