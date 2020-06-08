package com.project.apptruistic.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.*;

@Document(collation = "individuals")
public class Individual {

    @Id
    private String id;

    @NotBlank(message = "Please provide a first name")
    private String firstName;

    @NotEmpty(message = "Please provide a first name")

    private String lastName;

    @NotBlank(message = "Please provide an email address")
    @Email(message = "Please provide a valid email address")
    private String email;

    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String gender;

    @Length(min = 8, message = "Please provide a password with at least 8 characters")
    private String password;

    @NotBlank(message = "please include a street")
    private String street;

    @NotBlank(message = "please include a house number")
    private String houseNumber;

    @NotBlank(message = "please include a city")
    private String city;

    @NotBlank(message = "please include a zip code")
    private int zipCode;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private List<Opportunity> createdOpportunity = new ArrayList<>();

    public Individual() {
    }

    public Individual(@NotBlank(message = "Please provide a first name") String firstName, @NotEmpty(message = "Please provide a first name") String lastName, @NotBlank(message = "Please provide an email address") @Email(message = "Please provide a valid email address") String email, String phoneNumber, LocalDate birthday, String gender, @Length(min = 8, message = "Please provide a password with at least 8 characters") String password, @NotBlank(message = "please include a street") String street, @NotBlank(message = "please include a house number") String houseNumber, @NotBlank(message = "please include a city") String city, @NotBlank(message = "please include a zip code") int zipCode, Set<Role> roles, List<Opportunity> createdOpportunity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
        this.password = password;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.roles = roles;
        this.createdOpportunity = createdOpportunity;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Opportunity> getCreatedOpportunity() {
        return createdOpportunity;
    }

    public void setCreatedOpportunity(List<Opportunity> createdOpportunity) {
        this.createdOpportunity = createdOpportunity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return zipCode == that.zipCode &&
                Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(password, that.password) &&
                Objects.equals(street, that.street) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(city, that.city) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(createdOpportunity, that.createdOpportunity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, birthday, gender, password, street, houseNumber, city, zipCode, roles, createdOpportunity);
    }
}
