package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
}
