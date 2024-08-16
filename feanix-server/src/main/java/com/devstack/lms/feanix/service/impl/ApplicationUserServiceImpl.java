package com.devstack.lms.feanix.service.impl;

import com.devstack.lms.feanix.dto.request.RequestUserDto;
import com.devstack.lms.feanix.entity.ApplicationUser;
import com.devstack.lms.feanix.entity.UserRole;
import com.devstack.lms.feanix.exception.DuplicateEntryException;
import com.devstack.lms.feanix.exception.EntryNotFoundException;
import com.devstack.lms.feanix.repository.ApplicationUserRepo;
import com.devstack.lms.feanix.repository.ApplicationUserRoleRepo;
import com.devstack.lms.feanix.security.SupportSpringApplicationUser;
import com.devstack.lms.feanix.service.ApplicationUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.devstack.lms.feanix.security.ApplicationUserRole.ADMIN;
import static com.devstack.lms.feanix.security.ApplicationUserRole.USER;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    private final ApplicationUserRepo userRepo;
    private final ApplicationUserRoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> selectedUserData = userRepo.findByUsername(username);
        if (selectedUserData.isEmpty()) {
            throw new EntryNotFoundException(String.format("username % not found", username));
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRole u : selectedUserData.get().getRoles()) {
            if (u.getRoleName().equals("ADMIN")) {
                grantedAuthorities.addAll(ADMIN.grantedAuthorities());
            }
            if (u.getRoleName().equals("USER")) {
                grantedAuthorities.addAll(USER.grantedAuthorities());
            }
        }
        return new SupportSpringApplicationUser(
                selectedUserData.get().getUsername(),
                selectedUserData.get().getPassword(),
                selectedUserData.get().isAccountNonExpired(),
                selectedUserData.get().isAccountNonLocked(),
                selectedUserData.get().isCredentialNonExpired(),
                selectedUserData.get().isEnabled(),
                grantedAuthorities
        );
    }

    @Override
    public void create(RequestUserDto dto) {
        Optional<ApplicationUser> selectedUser = userRepo.findByUsername(dto.getUsername());
        if (selectedUser.isPresent()) {
            throw new DuplicateEntryException(String.format("User with email (%s) is exists", dto.getUsername()));
        }
        userRepo.save(createApplicationUser(dto));
    }

    @Override
    public void initializeAdmin() {
        Optional<ApplicationUser> selectedUser = userRepo.findByUsername("hasikasandaruwan.info@gmail.com");
        if (selectedUser.isPresent()) {
            return;
        }

        Optional<UserRole> selectedRole = roleRepo.findByRoleName("ADMIN");
        if (selectedRole.isEmpty()) {
            throw new EntryNotFoundException("role not found.");
        }

        Set<UserRole> selectedRoles = new HashSet<>();
        selectedRoles.add(selectedRole.get());


        userRepo.save(
                ApplicationUser.builder()
                        .userId(UUID.randomUUID().toString())
                        .username("hasikasandaruwan.info@gmail.com")
                        .password(passwordEncoder.encode("1234")) // must be encrypted //
                        .address("admin address")
                        .roles(selectedRoles)
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialNonExpired(true)
                        .isEnabled(true).build()
        );
    }

    private ApplicationUser createApplicationUser(RequestUserDto dto){
        if(dto==null){
            throw new RuntimeException("Something went wrong..");
        }
        Optional<UserRole> selectedRole =roleRepo.findByRoleName("USER");
        if(selectedRole.isEmpty()){
            throw new EntryNotFoundException("role not found");
        }
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(selectedRole.get());
        return ApplicationUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(dto.getUsername().trim())
                .password(passwordEncoder.encode(dto.getPassword()))
                .address(dto.getAddress())
                .roles(userRoles)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialNonExpired(true)
                .isEnabled(true).build();
    }

}
