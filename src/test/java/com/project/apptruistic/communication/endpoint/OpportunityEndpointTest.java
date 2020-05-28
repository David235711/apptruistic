package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.logic.VolunteerService;
import com.project.apptruistic.persistence.domain.Individual;
import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
/*
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class OpportunityEndpointTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    OpportunityService service;

    @SpyBean
    OpportunityEndpoint endpoint;

    String url = "/opportunities";

    @Test
    void postIsValid() {
        Opportunity opportunity = new Opportunity("help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
                List.of(new Individual()));
        testRestTemplate.postForObject(url, opportunity, Opportunity.class);

    }

}

 */