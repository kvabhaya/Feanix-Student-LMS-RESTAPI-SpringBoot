package com.devstack.lms.feanix.dto.request;

import com.devstack.lms.feanix.entity.CustomerOrder;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

public class RequestUserDto {
    private String userId;
    @Column(name = "username", length = 100, unique = true)
    private String username;
    @Column(name = "password", length = 750, nullable = false)
    private String password;
    @Column(name = "address", length = 750, nullable = false)
    private String address;

    @Column(name = "isAccountNonExpired", columnDefinition = "TINYINT")
    private boolean isAccountNonExpired;
    @Column(name = "isAccountNonLocked", columnDefinition = "TINYINT")
    private boolean isAccountNonLocked;
    @Column(name = "isCredentialNonExpired", columnDefinition = "TINYINT")
    private boolean isCredentialNonExpired;
    @Column(name = "isEnabled", columnDefinition = "TINYINT")
    private boolean isEnabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) //fetch-applicationUser ekkama eyage bind wela thiyen data tikath enwa
    private Set<CustomerOrder> customerOrders = new HashSet<>();
}
