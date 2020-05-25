package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class VolunteerServiceTest {

    @Autowired
    VolunteerService volunteerService;

    @MockBean
    VolunteerRepository repository;

    @Test
    void save() {
        volunteerService.save(new Volunteer("ciao", "ciao", "ciao", "ciao"));

        verify(repository).save(new Volunteer("ciao", "ciao", "ciao", "ciao"));
    }
}