package com.justin.healthyhabits.security;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    ADMIN_READ("admin:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
