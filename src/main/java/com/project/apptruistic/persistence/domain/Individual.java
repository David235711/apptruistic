package com.project.apptruistic.persistence.domain;

public class Individual implements Creator {
    private String name;
    @Override
    public String getName() {
        return name;
    }
}
