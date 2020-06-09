package com.project.apptruistic.security;

import com.project.apptruistic.persistence.domain.Individual;
import com.project.apptruistic.persistence.domain.Organization;
import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.IndividualRepository;
import com.project.apptruistic.persistence.repository.OrganizationRepository;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    IndividualRepository individualRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Volunteer> oVolunteer = volunteerRepository.findOneByEmail(email);
        if (oVolunteer.isPresent()) {
            return UserDetailsImpl.build(oVolunteer.get());
        }
        Optional<Individual> oIndividual = individualRepository.findOneByEmail(email);
        if (oIndividual.isPresent()) {
            return UserDetailsImpl.build(oIndividual.get());
        }
        Optional<Organization> optionalOrganization = organizationRepository.findOneByEmail(email);
        if (optionalOrganization.isPresent()) {
            return UserDetailsImpl.build(optionalOrganization.get());
        }
        throw new UsernameNotFoundException("Not found with email: " + email);
    }
}
