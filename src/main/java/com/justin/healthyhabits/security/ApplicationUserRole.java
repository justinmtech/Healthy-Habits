package com.justin.healthyhabits.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.justin.healthyhabits.security.ApplicationUserPermission.USER_READ;
import static com.justin.healthyhabits.security.ApplicationUserPermission.USER_WRITE;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(USER_READ, USER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
    return permissions;
    }
}
