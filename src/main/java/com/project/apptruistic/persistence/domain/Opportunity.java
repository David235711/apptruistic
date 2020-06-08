package com.project.apptruistic.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty(message = "Please provide a description")
    private String description;

    @NotNull(message = "Please provide a occurrence date (dd.MM.yyyy)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate occurDate;

    @NotNull(message = "Please provide a start time (HH:mm)")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull(message = "Please provide a end time (HH:mm)")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @NotEmpty(message = "Please provide a category")
    private String category;

    private String creator;
    private String creatorName;
    private boolean done;
    private int location;
    private int numberOfParticipants;
    private List<Volunteer> AcceptedVolunteers = new ArrayList<>();

    public Opportunity() {
    }

    public Opportunity(int hashcode, String name, String description, LocalDate occurDate, LocalTime startTime,
                       LocalTime endTime, String category, String creator, String creatorName, boolean done,
                       int location, int numberOfParticipants, List<Volunteer> acceptedVolunteers) {
        this.hashcode = hashcode;
        this.name = name;
        this.description = description;
        this.occurDate = occurDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.creator = creator;
        this.creatorName = creatorName;
        this.done = done;
        this.location = location;
        this.numberOfParticipants = numberOfParticipants;
    }

    public Opportunity(String name, String description, LocalDate occurDate, LocalTime startTime, LocalTime endTime, String category,
                       String creator, String creatorName, int location, int numberOfParticipants) {
        this.name = name;
        this.description = description;
        this.occurDate = occurDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.creator = creator;
        this.creatorName = creatorName;
        this.location = location;
        this.numberOfParticipants = numberOfParticipants;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
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