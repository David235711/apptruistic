package com.project.apptruistic.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Opportunity {

    private String id;

    @Indexed(unique = true)
    private int hashcode;

    @NotEmpty(message = "Please provide a name")
    private String name;

    @NotEmpty(message = "Please provide a category")
    private String category;

    private String creatorType;

    private String creatorName;

    @NotEmpty(message = "Please provide a short description (max 200 characters)")
    @Size(max = 280)
    private String shortDescription;

    //@NotEmpty(message = "Please provide a short description (max 2000 characters)")
    @Size(max = 2000)
    private String detailedDescription;

    @NotNull(message = "Please provide a occurrence date (dd.MM.yyyy)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate occurDate;

    @NotNull(message = "Please provide a start time (HH:mm)")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull(message = "Please provide a end time (HH:mm)")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @NotNull(message = "please provide a number of participants")
    private int numberOfParticipants;

    @NotBlank(message = "please include a street")
    private String street;

    @NotBlank(message = "please include a house number")
    private String houseNumber;

    @NotBlank(message = "please include a city")
    private String city;

    @NotNull(message = "please include a zip code")
    private int zipCode;

    private boolean done;

    private List<Volunteer> AcceptedVolunteers = new ArrayList<>();

    public Opportunity() {
    }

    public Opportunity(int hashcode, String name, String shortDescription, LocalDate occurDate, LocalTime startTime,
                       LocalTime endTime, String category, String creatorType, String creatorName, boolean done,
                       int numberOfParticipants, List<Volunteer> acceptedVolunteers) {
        this.hashcode = hashcode;
        this.name = name;
        this.shortDescription = shortDescription;
        this.occurDate = occurDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.creatorType = creatorType;
        this.creatorName = creatorName;
        this.done = done;
        this.numberOfParticipants = numberOfParticipants;
    }

    public Opportunity(String name, String shortDescription, LocalDate occurDate, LocalTime startTime, LocalTime endTime, String category,
                       String creatorType, String creatorName, int numberOfParticipants) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.occurDate = occurDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.creatorType = creatorType;
        this.creatorName = creatorName;
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public LocalDate getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(LocalDate occurDate) {
        this.occurDate = occurDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getHashcode() {
        return hashcode;
    }

    public void setHashcode(int hashcode) {
        this.hashcode = hashcode;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public List<Volunteer> getAcceptedVolunteers() {
        return AcceptedVolunteers;
    }

    public void setAcceptedVolunteers(List<Volunteer> acceptedVolunteers) {
        AcceptedVolunteers = acceptedVolunteers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return Objects.equals(hashcode, that.hashcode) &&
                Objects.equals(name, that.name) &&
                Objects.equals(occurDate, that.occurDate) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(creatorName, that.creatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashcode, name, occurDate, startTime, endTime, creatorName);
    }
}