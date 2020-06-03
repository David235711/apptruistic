package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Volunteer save(Volunteer volunteer) {
        return repository.findOneByEmail(volunteer.getEmail())
                .orElseGet(() -> createVolunteer(volunteer));
    }
    public List<Volunteer> getAll(){
        return repository.findAll();
    }

    private Volunteer createVolunteer(Volunteer volunteer) {
        String password = volunteer.getPassword();
        String encoded = passwordEncoder.encode(password);
        volunteer.setPassword(encoded);
        volunteer.setAuthorities(volunteerRole);
        return repository.save(volunteer);
    }
}
