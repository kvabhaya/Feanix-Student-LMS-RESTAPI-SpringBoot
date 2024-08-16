package com.devstack.lms.feanix.repository;

import com.devstack.lms.feanix.entity.ApplicationUser;
import com.devstack.lms.feanix.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicationUserRepo extends JpaRepository<ApplicationUser, String> {
    Optional<ApplicationUser> findByUsername(String username);


}
