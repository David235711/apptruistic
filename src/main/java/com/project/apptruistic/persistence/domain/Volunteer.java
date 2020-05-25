package com.project.apptruistic.persistence.domain;

import java.util.Objects;

public class Volunteer {

    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public Volunteer() {
    }

    public Volunteer(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
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
                Objects.equals(password, volunteer.password) &&
                Objects.equals(email, volunteer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, password, email);
    }
}