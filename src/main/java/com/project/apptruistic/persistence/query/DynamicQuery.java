package com.project.apptruistic.persistence.query;

import com.project.apptruistic.logic.CreatorType;
import com.project.apptruistic.logic.OpportunityCategory;

import java.time.LocalDateTime;

public class DynamicQuery {

    private int zipCode;
    private OpportunityCategory category;
    private String creatorName;
    private int numberOfParticipants;
    private CreatorType creatorType;
    private String startTime; // values: morning (6-12), afternoon (12-18), evening (18-24)
    private LocalDateTime occurDate;

    public DynamicQuery() {
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public OpportunityCategory getCategory() {
        return category;
    }

    public void setCategory(OpportunityCategory category) {
        this.category = category;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public CreatorType getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(CreatorType creatorType) {
        this.creatorType = creatorType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(LocalDateTime occurDate) {
        this.occurDate = occurDate;
    }
}
