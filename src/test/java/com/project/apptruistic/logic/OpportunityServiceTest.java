package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    OpportunityRepository opportunityRepository;

    @MockBean
    VolunteerRepository volunteerRepository;

    Opportunity opportunity = new Opportunity("name", OpportunityCategory.CARETAKING, CreatorType.INDIVIDUAL,
            "creatorId", "creatorName", "shortDescription", "detailedDescription",
            LocalDate.of(2020, 07, 15), LocalTime.now(), LocalTime.now(), 1, "street", "houseNumber", "Vienna", 1000);

    @Test
    void saveOpportunityAlreadyExists() {
        assertEquals(0, opportunity.getHashcode());
        when(opportunityRepository.findOneByHashcode(opportunity.hashCode()))
                .thenReturn(Optional.of(opportunity));

        opportunityService.save(opportunity);
        assertEquals(0, opportunity.getHashcode());
        verify(opportunityRepository).findOneByHashcode(any(Integer.class));
        verifyNoMoreInteractions(opportunityRepository);
    }


    @Test
    void saveOpportunityDoesNotExist() {
        assertEquals(0, opportunity.getHashcode());
        when(opportunityRepository.findOneByHashcode(opportunity.hashCode()))
                .thenReturn(Optional.empty());
        opportunityService.save(opportunity);
        verify(opportunityRepository).findOneByHashcode(any(Integer.class));
        assertNotNull(opportunity.getHashcode());
        verify(opportunityRepository).save(opportunity);
    }

    @Test
    void markAsDoneDoesNotFindEntry() {
        String id = "id";
        Optional<Opportunity> oExpected = Optional.empty();
        when(opportunityRepository.findById(id))
                .thenReturn(oExpected);
        Optional<Opportunity> oResult = opportunityService.markAsDone(id);
        assertEquals(oExpected, oResult);
        verify(opportunityRepository).findById(id);
    }

    @Test
    void markAsDoneFindsEntry() {
        String id = "id";
        Optional<Opportunity> oExpected = Optional.of(opportunity);
        when(opportunityRepository.findById(id)).thenReturn(oExpected);
        Optional<Opportunity> oResult = opportunityService.markAsDone(id);
        oExpected.get().setDone(true);
        assertEquals(oExpected, oResult);
        verify(opportunityRepository).findById(id);
        verify(opportunityRepository).save(oExpected.get());
    }

    @Test
    void getOneByName() {
        String name = "name";
        opportunityService.get(name);

        verify(opportunityRepository).findOneByName(name);
    }

    @Test
    void getOneById() {
        String id = "id";
        opportunityService.getById(id);

        verify(opportunityRepository).findById(id);
    }

    @Test
    void getAll() {
        opportunityService.getAll();

        verify(opportunityRepository).findAll();
    }



    @Test
    void findHeroOpportunities() {
        Opportunity expectedOpp = new Opportunity("name", OpportunityCategory.ANIMALS, CreatorType.INDIVIDUAL,
                "creatorId", "creatorName", "shortDescription", "detailedDescription",
                LocalDate.of(2020, 6, 22), LocalTime.now(), LocalTime.now(), 1, "street", "houseNumber", "Vienna", 1000);
        when(opportunityRepository.findAllByDoneFalse())
                .thenReturn(List.of(opportunity, expectedOpp));

        List<Opportunity> result = opportunityService.findHeroOpportunities();
        List<Opportunity> expected = List.of(expectedOpp);

        assertEquals(expected, result);

        verify(opportunityRepository).findAllByDoneFalse();
    }

    @Test
    void findSuggestedOpportunitiesDoesNotFindVolunteer() {
        String id = "id";
        when(volunteerRepository.findById(id))
                .thenReturn(Optional.empty());

        List<Opportunity> result = opportunityService.findSuggestedOpportunities(id);
        List<Opportunity> expected = new ArrayList<>();

        assertEquals(expected, result);

        verify(volunteerRepository).findById(id);
        verifyNoMoreInteractions(opportunityRepository);
    }

    @Test
    void findSuggestedOpportunitiesFindsVolunteer() {
        String id = "id";
        Volunteer volunteer = new Volunteer("ciao", "ciao", LocalDate.now(), "gender", "ciao", "ciao", CreatorType.INDIVIDUAL, OpportunityCategory.ANIMALS);
        Opportunity expectedOpp = new Opportunity("name", OpportunityCategory.ANIMALS, CreatorType.INDIVIDUAL,
                "creatorId", "creatorName", "shortDescription", "detailedDescription",
                LocalDate.of(2020, 6, 22), LocalTime.now(), LocalTime.now(), 1, "street", "houseNumber", "Vienna", 1000);

        when(volunteerRepository.findById(id))
                .thenReturn(Optional.of(volunteer));
        when(opportunityRepository.findAllByDoneFalse())
                .thenReturn(List.of(expectedOpp, opportunity));

        List<Opportunity> result = opportunityService.findSuggestedOpportunities(id);
        List<Opportunity> expected = List.of(expectedOpp);

        assertEquals(expected, result);

        verify(volunteerRepository).findById(id);
        verify(opportunityRepository).findAllByDoneFalse();
    }
}

