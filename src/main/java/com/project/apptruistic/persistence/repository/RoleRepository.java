package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.ERole;
import com.project.apptruistic.persistence.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}
