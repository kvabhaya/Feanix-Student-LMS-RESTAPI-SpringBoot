package com.devstack.lms.feanix.service.impl;

import com.devstack.lms.feanix.entity.UserRole;
import com.devstack.lms.feanix.repository.ApplicationUserRoleRepo;
import com.devstack.lms.feanix.service.ApplicationUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ApplicationUserRoleServiceImpl implements ApplicationUserRoleService {

    private final ApplicationUserRoleRepo userRoleRepo;


    @Override
    public void initializeRoles() {
        List<UserRole> applicationUserRoles
                = userRoleRepo.findAll();
        if(applicationUserRoles.isEmpty()){
            UserRole admin = UserRole.builder()
                    .roleId(UUID.randomUUID().toString())
                    .roleName("ADMIN")
                    .build();

            UserRole user = UserRole.builder()
                    .roleId(UUID.randomUUID().toString())
                    .roleName("USER")
                    .build();

            userRoleRepo.saveAll(List.of(admin,user));
        }
    }
}