package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Organization;
import com.project.apptruistic.persistence.repository.OrganizationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {
    private final OrganizationRepository repository;
    private final PasswordEncoder passwordEncoder;

    public OrganizationService(OrganizationRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Organization> getOrganizationByEmail(String email) {
        return repository.findOneByEmail(email);
    }

    public Organization save(Organization organization) {
        return repository.findOneByEmail(organization.getEmail())
                .orElseGet(() -> createOrganization(organization));
    }

    private Organization createOrganization(Organization organization) {
        System.out.println("oopsi, createOrganization in OrganizationService got called!");
        String password = organization.getPassword();
        String encoded = passwordEncoder.encode(password);
        organization.setPassword(encoded);
        return repository.save(organization);
    }

    public Organization edit(String email, Organization organization) {
        Optional<Organization> oOrganization = repository.findOneByEmail(email);

        if (oOrganization.isEmpty()) {
            return null;
        }
        //    Organization organization1 = oOrganization.get();
        return repository.save(organization);
    }
}
