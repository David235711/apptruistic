package com.project.apptruistic.persistence.domain;

public class Organization implements Creator {
    private String name;
    @Override
    public String getName() {
        return name;
    }
}
