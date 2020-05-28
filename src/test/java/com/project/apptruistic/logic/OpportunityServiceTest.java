package com.project.apptruistic.logic;
/*
import com.project.apptruistic.persistence.domain.Individual;
import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class OpportunityServiceTest {


    @Autowired
    OpportunityService opportunityService;
    @MockBean
    OpportunityRepository repository;


    @Test
    void saveOpportunityAlreadyExists() {
        Opportunity opportunity = new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
              List.of(new Individual()));

        when(repository.findOneByName(opportunity.getName()))
                .thenReturn(Optional.of(opportunity));


        when(opportunityService.exist(opportunity))
                .thenReturn(true);


      //  opportunityService.save(opportunity);
        verify(repository).findOneByName(opportunity.getName());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void saveOpportunityDoesNotExist() {
        Opportunity opportunity = new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
                List.of(new Individual()));
        opportunityService.save(opportunity);
        verify(repository).save(opportunity);

    }


}

 */