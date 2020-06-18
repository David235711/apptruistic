package com.project.apptruistic.logic;

public enum CreatorType {
    INDIVIDUAL("individual"),
    ORGANIZATION("organization"),
    BOTH("both");

    private final String representation;

    CreatorType(String representation) {
        this.representation = representation;
    }
}
