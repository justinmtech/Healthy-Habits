package com.justin.healthyhabits.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.justin.healthyhabits.security.ApplicationUserPermission.ADMIN_READ;
import static com.justin.healthyhabits.security.ApplicationUserPermission.USER_READ;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(USER_READ),
    ADMIN(Sets.newHashSet(USER_READ, ADMIN_READ)));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
    return permissions;
    }
}
