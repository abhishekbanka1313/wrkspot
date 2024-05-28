package com.company.firstApplication.firstApplicaton.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Slf4j
public class CustomUserPrincipal implements UserDetails {

    private Client client;

    public CustomUserPrincipal(Client client) {
        super();
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        log.info(this.client.getPassword());
        return this.client.getPassword();
    }

    @Override
    public String getUsername() {
        log.info(this.client.getClientName());
        return this.client.getClientName();
    }

    @Override
    public boolean isAccountNonExpired() {
        //In real life - get password from DB
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //In real life - get password from DB
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
