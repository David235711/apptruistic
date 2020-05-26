package com.project.apptruistic.security;

import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private final Volunteer volunteer;

    public UserPrincipal(Volunteer volunteer) {
        this.volunteer = volunteer;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> authorities = volunteer.getAuthorities();
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()]));
    }

    @Override
    public String getPassword() {
        return volunteer.getPassword();
    }

    @Override
    public String getUsername() {
        return volunteer.getEmail();
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
