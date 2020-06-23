package com.project.apptruistic.communication.dto;

import java.util.Objects;

public class OrganizationDTO {

    private String id;
    private String organizationName;
    private String contactFirstName;
    private String contactLastName;
    private String phoneNumber;
    private String street;
    private String houseNumber;
    private String city;
    private int zipCode;
    private String personalDescription;

    public OrganizationDTO() {
    }

    public OrganizationDTO(String id, String organizationName, String contactFirstName, String contactLastName, String phoneNumber, String street, String houseNumber, String city, int zipCode, String personalDescription) {
        this.id = id;
        this.organizationName = organizationName;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.personalDescription = personalDescription;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        OrganizationDTO that = (OrganizationDTO) o;
        return zipCode == that.zipCode &&
                Objects.equals(id, that.id) &&
                Objects.equals(organizationName, that.organizationName) &&
                Objects.equals(contactFirstName, that.contactFirstName) &&
                Objects.equals(contactLastName, that.contactLastName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(street, that.street) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(city, that.city) &&
                Objects.equals(personalDescription, that.personalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationName, contactFirstName, contactLastName, phoneNumber, street, houseNumber, city, zipCode, personalDescription);
    }
}
