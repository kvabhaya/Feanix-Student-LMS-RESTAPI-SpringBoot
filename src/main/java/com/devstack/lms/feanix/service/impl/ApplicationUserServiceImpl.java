package com.devstack.lms.feanix.service.impl;

import com.devstack.lms.feanix.dto.request.RequestUserDto;
import com.devstack.lms.feanix.entity.ApplicationUser;
import com.devstack.lms.feanix.entity.UserRole;
import com.devstack.lms.feanix.exception.EntryNotFoundException;
import com.devstack.lms.feanix.repository.ApplicationUserRepo;
import com.devstack.lms.feanix.security.SupportSpringApplicationUser;
import com.devstack.lms.feanix.service.ApplicationUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.devstack.lms.feanix.security.ApplicationUserRole.ADMIN;
import static com.devstack.lms.feanix.security.ApplicationUserRole.USER;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    private final ApplicationUserRepo userRepo;
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

    }
}