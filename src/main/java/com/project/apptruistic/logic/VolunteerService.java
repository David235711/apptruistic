package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class VolunteerService {

    private final VolunteerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final Set<String> volunteerRole;


    public VolunteerService(VolunteerRepository repository, PasswordEncoder passwordEncoder,
                            @Value("${apptruistic.volunteerRole}") Set<String> volunteerRole) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.volunteerRole = volunteerRole;
    }

    public Optional<Volunteer> getVolunteerByEmail(String email) {
        return repository.findOneByEmail(email);
    }

    public Volunteer save(Volunteer volunteer) {
        return repository.findOneByEmail(volunteer.getEmail())
                .orElseGet(() -> createVolunteer(volunteer));
    }

    private Volunteer createVolunteer(Volunteer volunteer) {
        System.out.println("oopsi, createVolunteer in VolunteerService got called!");
        String password = volunteer.getPassword();
        String encoded = passwordEncoder.encode(password);
        volunteer.setPassword(encoded);
//        volunteer.setAuthorities(volunteerRole);
        return repository.save(volunteer);
    }
}
