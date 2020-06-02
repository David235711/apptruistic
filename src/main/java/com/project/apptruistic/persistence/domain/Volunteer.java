package com.project.apptruistic.persistence.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Volunteer {

    private String id;

    @NotEmpty(message = "Please provide a first name")
    private String firstName;

    @NotEmpty(message = "Please provide a last name")
    private String lastName;

    private LocalDate dateOfBirth;

    @NotEmpty(message = "please provide a gender")
    private String gender;

    @Length(min = 8, message = "Please provide a password with at least 8 characters")
    private String password;

    @NotEmpty(message = "Please provide an email address")
    @Email(message = "Please provide a valid email address")
    private String email;

    private Set<String> authorities = new HashSet<>();

    public Volunteer() {
    }

    public Volunteer(String firstName, String lastName, LocalDate dateOfBirth, String gender, String password, String email, Set<String> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public Volunteer(String firstName, String lastName, LocalDate dateOfBirth, String gender, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
                Objects.equals(gender, volunteer.gender) &&
                Objects.equals(password, volunteer.password) &&
                Objects.equals(email, volunteer.email) &&
                Objects.equals(authorities, volunteer.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, gender, password, email, authorities);
    }
}


