package com.project.apptruistic.persistence.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Volunteer {

    private String id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    private Date dateOfBirth;
    @Min(8)
    private String password;
    @NotEmpty
    private String email;
    private Set<String> authorities = new HashSet<>();

    public Volunteer() {
    }

    public Volunteer(String firstName, String lastName, Date dateOfBirth, String password, String email, Set<String> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public Volunteer(@NotEmpty String firstName, @NotEmpty String lastName, Date dateOfBirth, @Min(8) String password, @NotEmpty String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(id, volunteer.id) &&
                Objects.equals(firstName, volunteer.firstName) &&
                Objects.equals(lastName, volunteer.lastName) &&
                Objects.equals(dateOfBirth, volunteer.dateOfBirth) &&
                Objects.equals(password, volunteer.password) &&
                Objects.equals(email, volunteer.email) &&
                Objects.equals(authorities, volunteer.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, password, email, authorities);
    }
}


