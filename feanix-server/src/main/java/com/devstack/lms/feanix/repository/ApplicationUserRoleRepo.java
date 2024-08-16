package com.devstack.lms.feanix.repository;

import com.devstack.lms.feanix.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicationUserRoleRepo extends JpaRepository<UserRole, String> {
    @Query(value = "SELECT * FROM user_role WHERE role_name=?1 LIMIT 1", nativeQuery = true)
    public Optional<UserRole> findByRoleName(String role);
}
