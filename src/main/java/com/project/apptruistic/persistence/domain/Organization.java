package com.project.apptruistic.persistence.domain;

public class Organization implements Creator {
    private String name;
    private String type;

    public Organization(String name) {
        this.name = name;
        this.type = "Organization";
    }

    @Override
    public String getCreatorName() {
        return name;
    }

    @Override
    public String getCreatorType() {
        return type;
    }
}
