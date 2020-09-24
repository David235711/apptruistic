package com.project.apptruistic.logic;

import com.project.apptruistic.communication.dto.VolunteerDTO;
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

    public VolunteerService(VolunteerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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
        return repository.save(volunteer);
    }

    public Optional<Volunteer> editVolunteer(String email, Volunteer newVolunteer) {
        Optional<Volunteer> oVolunteer = repository.findOneByEmail(email);
        if (oVolunteer.isEmpty()) {
            System.out.println("modify volunteer not found");
            return Optional.empty();
        }
        Volunteer volunteer = oVolunteer.get();
        volunteer.setFirstName(newVolunteer.getFirstName());
        volunteer.setLastName(newVolunteer.getLastName());
        volunteer.setDateOfBirth(newVolunteer.getDateOfBirth());
        volunteer.setGender(newVolunteer.getGender());
        volunteer.setCategory(newVolunteer.getCategory());
        volunteer.setPersonalDescription(newVolunteer.getPersonalDescription());
        volunteer.setPhoneNumber(newVolunteer.getPhoneNumber());
        volunteer.setPreferredType(newVolunteer.getPreferredType());
        String password = newVolunteer.getPassword();
        if(!password.equals(volunteer.getPassword())){
            String encode = passwordEncoder.encode(password);
            volunteer.setPassword(encode);
            System.out.println("old password");
        }
        repository.save(volunteer);
        return Optional.of(volunteer);
    }

    public Optional<VolunteerDTO> getVolunteerDtoById(String id) {
        Optional<Volunteer> oVolunteer = repository.findById(id);
        if (oVolunteer.isEmpty()) {
            System.out.println("Volunteer by id not found");
            return Optional.empty();
        }
        Volunteer volunteer = oVolunteer.get();
        VolunteerDTO volunteerDTO = new VolunteerDTO(
                volunteer.getId(),
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getGender(),
                volunteer.getCategory(),
                volunteer.getPersonalDescription()
        );
        return Optional.of(volunteerDTO);
    }
}
