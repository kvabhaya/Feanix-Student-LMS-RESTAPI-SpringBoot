package com.devstack.ecom.feanix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Embedded
    private FileResource fileResource;
}
