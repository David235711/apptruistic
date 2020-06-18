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
        Volunteer volunteer = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", "ciao", Set.of("categories"));
        when(repository.findOneByEmail(volunteer.getEmail()))
                .thenReturn(Optional.of(volunteer));

        volunteerService.save(volunteer);

        verify(repository).findOneByEmail(volunteer.getEmail());
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    void saveVolunteerDoesNotExist() {
        Volunteer volunteer = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", "ciao", Set.of("categories"));
        when(repository.findOneByEmail(volunteer.getEmail()))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(volunteer.getPassword()))
                .thenReturn(volunteer.getPassword());

        volunteerService.save(volunteer);

        verify(repository).findOneByEmail(volunteer.getEmail());
        verify(passwordEncoder).encode(volunteer.getPassword());
        Volunteer expected = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", "ciao", Set.of("categories"));
        verify(repository).save(expected);
    }
}