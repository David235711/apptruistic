package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OpportunityRepository extends MongoRepository<Opportunity, String> {

    Optional<Opportunity> findOneByName(String name);

    Optional<Opportunity> findOneByHashcode(int hashcode);

    List<Opportunity> findAllByDoneFalse();
//  List<Opportunity> findAllByFullFalse();
}
