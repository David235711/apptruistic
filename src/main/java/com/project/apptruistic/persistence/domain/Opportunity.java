package com.project.apptruistic.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

    private Creator creator;

    public Opportunity() {
    }

    public Opportunity(@NotEmpty String name, @NotEmpty String description, @NotEmpty LocalDate occurDate, @NotEmpty LocalTime startTime,
                       @NotEmpty LocalTime endTime, @NotEmpty String category, Creator creator) {
        this.name = name;
        this.description = description;
        this.occurDate = occurDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.creator =creator;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getOccurDate() {
        return occurDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getCategory() {
        return category;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOccurDate(LocalDate occurDate) {
        this.occurDate = occurDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
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
                Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, occurDate, startTime, endTime, category, creator);
    }

}
