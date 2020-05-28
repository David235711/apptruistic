package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OpportunityRepository extends MongoRepository<Opportunity, String> {
    Optional<Opportunity> findOneByName(String name);

}
