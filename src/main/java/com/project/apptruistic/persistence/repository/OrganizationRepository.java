package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
    Optional<Organization> findOneByEmail(String email);

    Boolean existsByEmail(String email);

}
