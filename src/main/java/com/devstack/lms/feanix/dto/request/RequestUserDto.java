package com.devstack.lms.feanix.dto.request;

import com.devstack.lms.feanix.entity.CustomerOrder;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

public class RequestUserDto {

    private String username;
    private String password;
    private String address;

}
