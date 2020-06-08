package com.project.apptruistic.persistence.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;

@Document(collection = "organizations")
public class Organization {

    @Id
    private String id;

    @NotBlank(message = "Please provide a name")
    private String organizationName;

    @NotBlank(message = "Please provide a first name")
    private String contactFirstName;

    @NotEmpty(message = "Please provide a last name")
    private String contactLastName;

    @NotBlank(message = "Please provide an email address")
    @Email(message = "Please provide a valid email address")
    private String email;

    private String phoneNumber;

    @Length(min = 8, message = "Please provide a password with at least 8 characters")
    private String password;

    @NotBlank(message = "please include a street")
    private String street;

    @NotBlank(message = "please include a house number")
    private String houseNumber;

    @NotBlank(message = "please include a city")
    private String city;

    @NotNull(message = "please include a zip code")
    private int zipCode;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private List<Opportunity> createdOpportunity = new ArrayList<>();

    public Organization() {
    }

    public Organization(
            String organizationName,
            String contactFirstName,
            String contactLastName,
            String email,
            String phoneNumber,
            String password,
            String street,
            String houseNumber,
            String city,
            int zipCode,
            Set<Role> roles,
            List<Opportunity> createdOpportunity
    ) {
        this.organizationName = organizationName;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.roles = roles;
        this.createdOpportunity = createdOpportunity;
    }

    public Organization(
            String organizationName,
            String contactFirstName,
            String contactLastName,
            String email,
            String phoneNumber,
            String password,
            String street,
            String houseNumber,
            String city,
            int zipCode
    ) {
        this.organizationName = organizationName;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
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
        Organization that = (Organization) o;
        return zipCode == that.zipCode &&
                Objects.equals(id, that.id) &&
                Objects.equals(organizationName, that.organizationName) &&
                Objects.equals(contactFirstName, that.contactFirstName) &&
                Objects.equals(contactLastName, that.contactLastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(password, that.password) &&
                Objects.equals(street, that.street) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(city, that.city) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(createdOpportunity, that.createdOpportunity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationName, contactFirstName, contactLastName, email, phoneNumber, password, street, houseNumber, city, zipCode, roles, createdOpportunity);
    }
}