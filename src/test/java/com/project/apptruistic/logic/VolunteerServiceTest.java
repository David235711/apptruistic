package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class VolunteerServiceTest {

    @Autowired
    VolunteerService volunteerService;

    @MockBean
    VolunteerRepository repository;
    @MockBean
    PasswordEncoder passwordEncoder;
    @Value("${apptruistic.volunteerRole}")
    Set<String> volunteerRole;

    @Test
    void saveAlreadyExists() {
        Volunteer volunteer = new Volunteer("ciao", "ciao", Date.valueOf(LocalDate.now()), "ciao", "ciao", volunteerRole);
        when(repository.findOneByEmail(volunteer.getEmail()))
                .thenReturn(Optional.of(volunteer));

        volunteerService.save(volunteer);

        verify(repository).findOneByEmail(volunteer.getEmail());
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    void saveVolunteerDoesNotExist() {
        Volunteer volunteer = new Volunteer("ciao", "ciao", Date.valueOf(LocalDate.now()), "ciao", "ciao");
        when(repository.findOneByEmail(volunteer.getEmail()))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(volunteer.getPassword()))
                .thenReturn(volunteer.getPassword());

        volunteerService.save(volunteer);

        verify(repository).findOneByEmail(volunteer.getEmail());
        verify(passwordEncoder).encode(volunteer.getPassword());
        Volunteer expected = new Volunteer("ciao", "ciao", Date.valueOf(LocalDate.now()), "ciao", "ciao", volunteerRole);
        verify(repository).save(expected);
    }
}