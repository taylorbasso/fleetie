package com.fleetie.pojo;

import com.fleetie.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class UserPrincipal implements UserDetails {

    private final Long id;
    private final UUID uuid;
    private final String username;
    private String password;
    private final Instant dateCreated;
    private Instant dateModified;
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private Set<? extends GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.uuid = user.getUuid();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.dateCreated = user.getDateCreated();
        this.dateModified = user.getDateModified();
        this.enabled = user.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
