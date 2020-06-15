package com.project.apptruistic.logic;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OpportunityCategory {
    HOUSEHOLD("Household"),
    ENVIRONMENT("Environment"),
    ANIMALS("Animals"),
    REFUGEES("Refugees"),
    CARETAKING("Care-taking"),
    TRANSPORT("Transport"),
    ITSUPPORT("IT support"),
    TEACHING("Teaching/Tutoring");

    private final String representation;

    OpportunityCategory(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
