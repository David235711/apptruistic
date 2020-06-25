package com.project.apptruistic.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.apptruistic.logic.CreatorType;
import com.project.apptruistic.logic.OpportunityCategory;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Document(collection = "opportunity")
public class Opportunity {
    //    @Id
    private String id;

    @Indexed(unique = true)
    private int hashcode;

   private LocalDateTime timestamp;

    @NotEmpty(message = "Please provide a name")
    private String name;

    @NotNull(message = "Please provide a category")
    private OpportunityCategory category;

    @NotNull(message = "Specify creator type")
    private CreatorType creatorType;

    @NotEmpty(message = "Specify creator id")
    private String creatorId;

    @NotEmpty(message = "Specify creator name")
    private String creatorName;

    @NotEmpty(message = "Please provide a short description (maximum of 200 characters)")
    @Size(max = 280)
    private String shortDescription;

    @NotEmpty(message = "Please provide a detailed description (maximum of 2000 characters)")
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

    private long durationInMinutes;

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

    @DBRef
    private List<Volunteer> acceptedVolunteers = new ArrayList<>();

    public Opportunity() {
    }

    public Opportunity(
            int hashcode,
            String name,
            OpportunityCategory category,
            CreatorType creatorType,
            String creatorId,
            String creatorName,
            String shortDescription,
            String detailedDescription,
            LocalDate occurDate,
            LocalTime startTime,
            LocalTime endTime,
            long durationInMinutes,
            int numberOfParticipants,
            String street,
            String houseNumber,
            String city,
            int zipCode,
            boolean done,
            List<Volunteer> acceptedVolunteers
    ) {
        this.hashcode = hashcode;
        this.name = name;
        this.category = category;
        this.creatorType = creatorType;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.occurDate = occurDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInMinutes = durationInMinutes;
        this.numberOfParticipants = numberOfParticipants;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.done = done;
        this.acceptedVolunteers = acceptedVolunteers;
    }

    public Opportunity(
            String name,
            OpportunityCategory category,
            CreatorType creatorType,
            String creatorId,
            String creatorName,
            String shortDescription,
            String detailedDescription,
            LocalDate occurDate,
            LocalTime startTime,
            LocalTime endTime,
            int numberOfParticipants,
            String street,
            String houseNumber,
            String city,
            int zipCode
    ) {
        this.name = name;
        this.category = category;
        this.creatorType = creatorType;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.occurDate = occurDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfParticipants = numberOfParticipants;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
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

    public OpportunityCategory getCategory() {
        return category;
    }

    public void setCategory(OpportunityCategory category) {
        this.category = category;
    }

    public CreatorType getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(CreatorType creatorType) {
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
        return acceptedVolunteers;
    }

    public void setAcceptedVolunteers(List<Volunteer> acceptedVolunteers) {
        this.acceptedVolunteers = acceptedVolunteers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return name.equals(that.name) &&
                creatorId.equals(that.creatorId) &&
                occurDate.equals(that.occurDate) &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creatorId, occurDate, startTime, endTime);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", creatorType=" + creatorType +
                ", occurDate=" + occurDate +
                ", startTime=" + startTime +
                ", numberOfParticipants=" + numberOfParticipants +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                ", done=" + done +
                '}';
    }
}
