package com.test.fooddeliverytest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityConstants {

    public static final String SECRET = "deliverTHEf00D";
    // Days token time
    public static final long EXPIRATION_TIME = 423_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final UserDetails ADMIN_USER = new UserDetails() {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return authorities;
        }

        @Override
        public String getPassword() {
            return "password123";
        }

        @Override
        public String getUsername() {
            return "admin123";
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
    };

    public static final Long ADMIN_USER_ID = -1L;

}
