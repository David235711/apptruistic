package com.project.apptruistic.logic;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OpportunityCategory {
    HOUSEHOLD("household"),
    ENVIRONMENT("environment"),
    ANIMALS("animals"),
    REFUGEES("refugees"),
    CARETAKING("care-taking"),
    TRANSPORT("transport"),
    ITSUPPORT("IT support"),
    TEACHING("teaching/tutoring");

    private final String representation;

    OpportunityCategory(String representation) {
        this.representation = representation;
    }
}
