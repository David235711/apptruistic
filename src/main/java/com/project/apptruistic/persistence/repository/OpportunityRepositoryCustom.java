package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.Opportunity;

import java.util.List;

public interface OpportunityRepositoryCustom {

    List<Opportunity> query(DynamicQuery dynamicQuery);
}
