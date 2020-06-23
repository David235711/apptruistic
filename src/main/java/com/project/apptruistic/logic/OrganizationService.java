package com.project.apptruistic.logic;

import com.project.apptruistic.communication.dto.OrganizationDTO;
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

    public Optional<Organization> editOrganization(String email, Organization newOrganization) {
        Optional<Organization> oOrganization = repository.findOneByEmail(email);
        if (oOrganization.isEmpty()) {
            return Optional.empty();
        }
        Organization organization = oOrganization.get();
        organization.setOrganizationName(newOrganization.getOrganizationName());
        organization.setContactFirstName(newOrganization.getContactFirstName());
        organization.setContactLastName(newOrganization.getContactLastName());
        organization.setPhoneNumber(newOrganization.getPhoneNumber());
        organization.setStreet(newOrganization.getStreet());
        organization.setHouseNumber(newOrganization.getHouseNumber());
        organization.setCity(newOrganization.getCity());
        organization.setZipCode(newOrganization.getZipCode());
        organization.setPersonalDescription(newOrganization.getPersonalDescription());
        String password = newOrganization.getPassword();
        if (!password.equals(organization.getPassword())) {
            String encode = passwordEncoder.encode(password);
            organization.setPassword(encode);
            System.out.println("old password");
        }
        repository.save(organization);
        return Optional.of(organization);
    }

    public Optional<OrganizationDTO> getOrganizationDTOById(String id) {
        Optional<Organization> oOrganization = repository.findById(id);
        if (oOrganization.isEmpty()) {
            System.out.println("Organization DTO not found");
            return Optional.empty();
        }
        Organization organization = oOrganization.get();
        OrganizationDTO organizationDTO = new OrganizationDTO(organization.getId(), organization.getOrganizationName(), organization.getContactFirstName(), organization.getContactLastName(), organization.getPhoneNumber(), organization.getStreet(), organization.getHouseNumber(), organization.getCity(), organization.getZipCode(), organization.getPersonalDescription());
        return Optional.of(organizationDTO);
    }
}
