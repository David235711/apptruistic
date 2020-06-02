package com.project.apptruistic.persistence.domain;

public class Individual implements Creator {
    private String name;
    private String type;

    public Individual(String name) {
        this.name = name;
        this.type = "individual";
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
