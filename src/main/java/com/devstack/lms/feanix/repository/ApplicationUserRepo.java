package com.devstack.lms.feanix.repository;

import com.devstack.lms.feanix.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepo extends JpaRepository<ApplicationUser, String> {
    Optional<ApplicationUser> findByUsername(String username);
}
