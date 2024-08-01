package com.devstack.lms.feanix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "application_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ApplicationUser {
    @Id
    @Column(name = "user_id", length = 80)
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

    @ManyToMany
    @JoinTable(name = "user_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles;
}
