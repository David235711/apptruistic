package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class VolunteerServiceTest {
    @MockBean
    ApplicationRunner applicationRunner;

    @Autowired
    VolunteerService volunteerService;

    @MockBean
    VolunteerRepository repository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    void findOneByEmail() {
        String email = "email";
        volunteerService.getVolunteerByEmail(email);

        verify(repository).findOneByEmail(email);
    }

    @Test
    void saveAlreadyExists() {
        Volunteer volunteer = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", "ciao", CreatorType.INDIVIDUAL, OpportunityCategory.ANIMALS);
        when(repository.findOneByEmail(volunteer.getEmail()))
                .thenReturn(Optional.of(volunteer));

        volunteerService.save(volunteer);

        verify(repository).findOneByEmail(volunteer.getEmail());
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    void saveVolunteerDoesNotExist() {
        Volunteer volunteer = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", "ciao", CreatorType.INDIVIDUAL, OpportunityCategory.ANIMALS);
        when(repository.findOneByEmail(volunteer.getEmail()))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(volunteer.getPassword()))
                .thenReturn(volunteer.getPassword());

        volunteerService.save(volunteer);

        verify(repository).findOneByEmail(volunteer.getEmail());
        verify(passwordEncoder).encode(volunteer.getPassword());
        Volunteer expected = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", "ciao", CreatorType.INDIVIDUAL, OpportunityCategory.ANIMALS);
        verify(repository).save(expected);
    }
    @Test
    void editVolunteerDoesNotFindVolunteer(){
        String email = "email";
        Volunteer volunteer = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", email, CreatorType.INDIVIDUAL, OpportunityCategory.ANIMALS);

        when(repository.findOneByEmail(email))
                .thenReturn(Optional.empty());

        volunteerService.editVolunteer(email, volunteer);

        verify(repository).findOneByEmail(email);
        verifyNoInteractions(passwordEncoder);
        verifyNoMoreInteractions(repository);
    }
    @Test
    void editVolunteerFindsVolunteer(){
        String email = "email";
        Volunteer volunteer = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", email, CreatorType.INDIVIDUAL, OpportunityCategory.ANIMALS);
        when(repository.findOneByEmail(email))
                .thenReturn(Optional.of(volunteer));
        when(passwordEncoder.encode(volunteer.getPassword()))
                .thenReturn(volunteer.getPassword());

        volunteerService.editVolunteer(email, volunteer);

        verify(repository).findOneByEmail(email);
        verify(repository).save(volunteer);
    }
}