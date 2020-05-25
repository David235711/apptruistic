package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.sql.Date;
import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@SpringBootTest
class VolunteerServiceTest {

    @Autowired
    VolunteerService volunteerService;

    @MockBean
    VolunteerRepository repository;

    @Test
    void save() {
        volunteerService.save(new Volunteer("ciao", "ciao", Date.valueOf(LocalDate.now()), "ciao", "ciao"));

        verify(repository).save(new Volunteer("ciao", "ciao", Date.valueOf(LocalDate.now()), "ciao", "ciao"));
    }
}