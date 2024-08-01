package com.devstack.lms.feanix.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(
            ApplicationUserPermission.PRODUCT_WRITE,
            ApplicationUserPermission.ORDER_WRITE,
            ApplicationUserPermission.ORDER_READ
    )),
    ADMIN(Sets.newHashSet(
            ApplicationUserPermission.PRODUCT_READ,
            ApplicationUserPermission.PRODUCT_WRITE,
            ApplicationUserPermission.USER_ROLE_WRITE,
            ApplicationUserPermission.ORDER_READ,
            ApplicationUserPermission.ORDER_WRITE
    ));
    private final Set<ApplicationUserPermission> applicationUserPermission;

    ApplicationUserRole(Set<ApplicationUserPermission> applicationUserPermission) {
        this.applicationUserPermission = applicationUserPermission;
    }
    public Set<ApplicationUserPermission> getApplicationUserPermission(){
        return applicationUserPermission;
    }
    public Set<SimpleGrantedAuthority> grantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getApplicationUserPermission()
                .stream().map(permission->
                        new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
