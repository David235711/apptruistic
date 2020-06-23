package com.project.apptruistic.communication.endpoint.dto;

import java.util.Objects;

public class IndividualDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private int zipCode;
    private String personalDescription;

    public IndividualDTO() {
    }

    public IndividualDTO(String id, String firstName, String lastName, String gender, int zipCode, String personalDescription) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.zipCode = zipCode;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
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
        IndividualDTO that = (IndividualDTO) o;
        return zipCode == that.zipCode &&
                Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(personalDescription, that.personalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, zipCode, personalDescription);
    }
}
