package com.test.fooddeliverytest.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.fooddeliverytest.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrincipal extends User implements UserDetails, Serializable {

    @JsonIgnore
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String username, String password, UserType userType,
                         Collection<? extends GrantedAuthority> authorities) {
        super(userType, username, password);
        super.setId(id);
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getUserType().toString()));
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getUserType(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserPrincipal))
            return false;

        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(super.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }
}
