package com.project.apptruistic.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Opportunity {

    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate occurDate;
    @NotEmpty
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;
    @NotEmpty
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
    @NotEmpty
    private String category;

    private String creator;
    private String creatorName;

    public Opportunity() {
    }

    public Opportunity(@NotEmpty String name, @NotEmpty String description, @NotEmpty LocalDate occurDate, @NotEmpty LocalTime startTime, @NotEmpty LocalTime endTime, @NotEmpty String category, String creator, String creatorName) {
        this.name = name;
        this.description = description;
        this.occurDate = occurDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.creator = creator;
        this.creatorName = creatorName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(occurDate, that.occurDate) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(category, that.category) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(creatorName, that.creatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, occurDate, startTime, endTime, category, creator, creatorName);
    }
}
