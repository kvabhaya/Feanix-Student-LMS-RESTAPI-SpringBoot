package com.devstack.lms.feanix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "user_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {
    @Id
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "role_name", unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set <ApplicationUser> users;
}
