package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VolunteerRepository extends MongoRepository<Volunteer, String> {

    Optional<Volunteer> findOneByEmail(String email);

    Boolean existsByEmail(String email);
}
