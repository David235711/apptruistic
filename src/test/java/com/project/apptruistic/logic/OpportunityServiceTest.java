package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class OpportunityServiceTest {

    @Autowired
    OpportunityService opportunityService;

    @MockBean
    OpportunityRepository repository;
    String id = "id";
    Opportunity opportunity = new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
            "creator", "creatorName", "Vienna", 5);

    @Test
    void saveOpportunityAlreadyExists() {

        assertEquals(0, opportunity.getHashcode());
        when(repository.findOneByHashcode(opportunity.hashCode()))
                .thenReturn(Optional.of(opportunity));

        opportunityService.save(opportunity);
        assertEquals(0, opportunity.getHashcode());
        verify(repository).findOneByHashcode(any(Integer.class));
        verifyNoMoreInteractions(repository);
    }


    @Test
    void saveOpportunityDoesNotExist() {

        assertEquals(0, opportunity.getHashcode());
        when(repository.findOneByHashcode(opportunity.hashCode()))
                .thenReturn(Optional.empty());
        opportunityService.save(opportunity);
        verify(repository).findOneByHashcode(any(Integer.class));
        assertNotNull(opportunity.getHashcode());
        verify(repository).save(opportunity);
    }

    @Test
    void markAsDoneDoesNotFindEntry() {

        Optional<Opportunity> oExpected = Optional.empty();
        when(repository.findById(id))
                .thenReturn(oExpected);
        Optional<Opportunity> oResult = opportunityService.markAsDone(id);
        assertEquals(oExpected, oResult);
        verify(repository).findById(id);
    }

    @Test
    void markAsDoneFindsEntry() {

        Optional<Opportunity> oExpected = Optional.of(new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
                "individual", "creatorName", "Vienna", 1));
        when(repository.findById(id))
                .thenReturn(oExpected);
        Optional<Opportunity> oResult = opportunityService.markAsDone(id);
        oExpected.get().setDone(true);
        assertEquals(oExpected, oResult);
        verify(repository).findById(id);
        verify(repository).save(oExpected.get());
    }

    @Test
    void calculateNumberOfNeededVolunteers() {
        Opportunity opportunity1 = new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
                "creator", "creatorName", "Vienna", 5, List.of());
        int numOfNeededVolunteers = opportunityService.calculateNumberOfNeededVolunteers(opportunity1);
        int expected = opportunity1.getNumberOfParticipants() - opportunity1.getAcceptedVolunteers().size();
        assertEquals(expected, numOfNeededVolunteers);
    }
}

