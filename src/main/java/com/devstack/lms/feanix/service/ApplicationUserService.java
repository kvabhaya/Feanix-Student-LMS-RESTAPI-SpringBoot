package com.devstack.lms.feanix.service;

import com.devstack.lms.feanix.dto.request.RequestUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ApplicationUserService extends UserDetailsService {
    public void create(RequestUserDto dto);
}