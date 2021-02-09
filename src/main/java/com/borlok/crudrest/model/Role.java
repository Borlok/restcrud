package com.borlok.crudrest.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permits.AUTHORITY_READ)),
    ADMIN(Set.of(Permits.AUTHORITY_WRITE, Permits.AUTHORITY_READ));

    private Set<Permits> permits;

    Role(Set<Permits> permits) {
        this.permits = permits;
    }

    public Set<Permits> getPermits() {
        return permits;
    }

    public Set<SimpleGrantedAuthority> getAuthorities () {
        return getPermits().stream().map(x -> new SimpleGrantedAuthority(x.getPermit())).collect(Collectors.toSet());
    }

}
