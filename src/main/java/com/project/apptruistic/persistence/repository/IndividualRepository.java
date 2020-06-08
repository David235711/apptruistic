package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.Individual;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndividualRepository extends MongoRepository<Individual, String> {
}
