package com.project.apptruistic.logic;

public enum CreatorType {
    INDIVIDUAL("individual"),
    ORGANIZATION("organization");

    private final String representation;

    CreatorType(String representation) {
        this.representation = representation;
    }
}
