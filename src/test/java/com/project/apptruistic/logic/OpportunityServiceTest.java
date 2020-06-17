package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class OpportunityServiceTest {
    @MockBean
    ApplicationRunner applicationRunner;

    @Autowired
    OpportunityService opportunityService;

    @MockBean
    OpportunityRepository repository;

    Opportunity opportunity = new Opportunity("name", OpportunityCategory.ANIMALS, CreatorType.INDIVIDUAL,
            "creatorId", "creatorName", "shortDescription", "detailedDescription",
            LocalDate.now(), LocalTime.now(), LocalTime.now(), 1, "street", "houseNumber", "Vienna", 1000);

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
        String id = "id";
        Optional<Opportunity> oExpected = Optional.empty();
        when(repository.findById(id))
                .thenReturn(oExpected);
        Optional<Opportunity> oResult = opportunityService.markAsDone(id);
        assertEquals(oExpected, oResult);
        verify(repository).findById(id);
    }

    @Test
    void markAsDoneFindsEntry() {
        String id = "id";
        Optional<Opportunity> oExpected = Optional.of(opportunity);
        when(repository.findById(id)).thenReturn(oExpected);
        Optional<Opportunity> oResult = opportunityService.markAsDone(id);
        oExpected.get().setDone(true);
        assertEquals(oExpected, oResult);
        verify(repository).findById(id);
        verify(repository).save(oExpected.get());
    }
}

