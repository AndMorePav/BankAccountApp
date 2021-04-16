package com.bank.reckoning.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumeration of user roles.
 */
public enum Role implements GrantedAuthority {

    /**
     * Admin.
     */
    ROLE_ADMIN,

    /**
     * User.
     */
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
