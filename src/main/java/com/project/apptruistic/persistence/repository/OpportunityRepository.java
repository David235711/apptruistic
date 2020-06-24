package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.logic.CreatorType;
import com.project.apptruistic.logic.OpportunityCategory;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface OpportunityRepository extends MongoRepository<Opportunity, String> {

    Optional<Opportunity> findOneByName(String name);

    Optional<Opportunity> findOneByHashcode(int hashcode);

    List<Opportunity> findAllByDoneFalse();

    //  List<Opportunity> findAllByZipCode(int zipCode);
    List<Opportunity> findAllByZipCodeAndDoneFalse(int zipCode);

    List<Opportunity> findAllByCategoryAndDoneFalse(OpportunityCategory category);

    List<Opportunity> findAllByCreatorNameAndDoneFalse(String creatorName);

    List<Opportunity> findAllByCreatorTypeAndDoneFalse(CreatorType creatorType);

    List<Opportunity> findAllByOccurDateAndDoneFalse(LocalDate date);

    List<Opportunity> findAllByStartTime(LocalTime startTime);
    // List<Opportunity> findAllBy$ZAndZipCode$AndStartTime (int zipcode)
}
