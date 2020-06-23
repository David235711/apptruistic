package com.project.apptruistic.communication.endpoint.dto;

import com.project.apptruistic.logic.OpportunityCategory;

import java.util.Objects;

public class VolunteerDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private OpportunityCategory category;
    private String personalDescription;

    public VolunteerDTO() {
    }

    public VolunteerDTO(String id, String firstName, String lastName, String gender, OpportunityCategory category, String personalDescription) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.category = category;
        this.personalDescription = personalDescription;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public OpportunityCategory getCategory() {
        return category;
    }

    public void setCategory(OpportunityCategory category) {
        this.category = category;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolunteerDTO that = (VolunteerDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(gender, that.gender) &&
                category == that.category &&
                Objects.equals(personalDescription, that.personalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, category, personalDescription);
    }
}
