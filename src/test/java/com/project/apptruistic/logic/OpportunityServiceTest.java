package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
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


    @Test
    void saveOpportunityAlreadyExists() {
        Opportunity opportunity = new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
                "creator", "creatorName", false, "Vienna");
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
        Opportunity opportunity = new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
                "individual", "creatorName", false, "Vienna");
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
        Optional<ToDo> oExpected = Optional.of(new ToDo("test"));
        when(toDoRepository.findById(id))
                .thenReturn(oExpected);

        Optional<ToDo> oResult = toDoManager.markAsDone(id);

        oExpected.get().setDone(true);
        assertEquals(oExpected, oResult);
        verify(toDoRepository).findById(id);
        verify(toDoRepository).save(oExpected.get());
    }




}

