package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
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
                "creator", "creatorName");

        when(repository.findOneByName(opportunity.getName()))
                .thenReturn(Optional.of(opportunity));

        opportunityService.save(opportunity);

        verify(repository).findOneByName(opportunity.getName());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void saveOpportunityDoesNotExist() {
        Opportunity opportunity = new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
                "individual", "creatorName");
        when(repository.findOneByName(opportunity.getName()))
                .thenReturn(Optional.empty());

        opportunityService.save(opportunity);

        verify(repository).findOneByName(opportunity.getName());
        verify(repository).save(opportunity);

    }
}

