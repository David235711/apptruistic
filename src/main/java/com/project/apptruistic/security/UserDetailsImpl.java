package com.project.apptruistic.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.apptruistic.persistence.domain.Individual;
import com.project.apptruistic.persistence.domain.Organization;
import com.project.apptruistic.persistence.domain.Role;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    public static UserDetailsImpl build(Volunteer volunteer) {
        List<GrantedAuthority> authorities = getGrantedAuthorities(volunteer.getRoles());

        return new UserDetailsImpl(
                volunteer.getId(),
                volunteer.getEmail(),
                volunteer.getPassword(),
                authorities);
    }

    public static UserDetailsImpl build(Individual individual) {
        List<GrantedAuthority> authorities = getGrantedAuthorities(individual.getRoles());

        return new UserDetailsImpl(
                individual.getId(),
                individual.getEmail(),
                individual.getPassword(),
                authorities);
    }

    public static UserDetailsImpl build(Organization organization) {
        List<GrantedAuthority> authorities = getGrantedAuthorities(organization.getRoles());

        return new UserDetailsImpl(
                organization.getId(),
                organization.getEmail(),
                organization.getPassword(),
                authorities);
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
        return email;
    }

    public String getId() {
        return id;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl that = (UserDetailsImpl) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
