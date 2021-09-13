package com.justin.healthyhabits.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private String role;
    private List<GrantedAuthority> authorities;
    private List<Habit> habits;

    public CustomUserDetails(String username, String password, String role, List<GrantedAuthority> authorities, List<Habit> habits) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.habits = habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public String getRole() {
        return role;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
