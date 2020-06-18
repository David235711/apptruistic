package com.project.apptruistic.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.apptruistic.logic.CreatorType;
import com.project.apptruistic.logic.OpportunityCategory;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Document(collection = "volunteers")
public class Volunteer {

    @Id
    private String id;

    @NotEmpty(message = "Please provide a first name")
    private String firstName;

    @NotEmpty(message = "Please provide a last name")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "please provide a gender")
    private String gender;

    @Length(min = 8, message = "Please provide a password with at least 8 characters")
    private String password;

    @NotEmpty(message = "Please provide an email address")
    @Email(message = "Please provide a valid email address")
    private String email;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private CreatorType preferredType;

    private Set<OpportunityCategory> categories = new HashSet<>();

    @DBRef
    private List<Opportunity> appliedOpportunities = new ArrayList<>();

    @DBRef
    private List<Opportunity> declinedOpportunities = new ArrayList<>();

    @DBRef
    private List<Opportunity> acceptedOpportunities = new ArrayList<>();

    @Size(max = 2000)
    private String personalDescription;

    private String phoneNumber;

    public Volunteer() {
    }

    public Volunteer(String firstName, String lastName, LocalDate dateOfBirth, String gender, String password, String email, Set<Role> roles, Set<OpportunityCategory> categories) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.categories = categories;
    }

    public Volunteer(String firstName, String lastName, LocalDate dateOfBirth, String gender, String password, String email, Set<OpportunityCategory> categories) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.categories = categories;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public Set<OpportunityCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<OpportunityCategory> categories) {
        this.categories = categories;
    }

    public List<Opportunity> getAppliedOpportunities() {
        return appliedOpportunities;
    }

    public void setAppliedOpportunities(List<Opportunity> appliedOpportunities) {
        this.appliedOpportunities = appliedOpportunities;
    }

    public List<Opportunity> getAcceptedOpportunities() {
        return acceptedOpportunities;
    }

    public void setAcceptedOpportunities(List<Opportunity> acceptedOpportunities) {
        this.acceptedOpportunities = acceptedOpportunities;
    }

    public List<Opportunity> getDeclinedOpportunities() {
        return declinedOpportunities;
    }

    public void setDeclinedOpportunities(List<Opportunity> declinedOpportunities) {
        this.declinedOpportunities = declinedOpportunities;
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
                Objects.equals(roles, volunteer.roles) &&
                Objects.equals(categories, volunteer.categories) &&
                Objects.equals(appliedOpportunities, volunteer.appliedOpportunities) &&
                Objects.equals(declinedOpportunities, volunteer.declinedOpportunities) &&
                Objects.equals(acceptedOpportunities, volunteer.acceptedOpportunities) &&
                Objects.equals(personalDescription, volunteer.personalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, gender, password, email, roles, categories, appliedOpportunities, declinedOpportunities, acceptedOpportunities, personalDescription);
    }

    public CreatorType getPreferredType() {
        return preferredType;
    }

    public void setPreferredType(CreatorType preferredType) {
        this.preferredType = preferredType;
    }
}


