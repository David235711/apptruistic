package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class OpportunityEndpointTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    OpportunityService service;

    String url = "/opportunities";

    @Test
    void post() {
        Opportunity opportunity = new Opportunity(1, "help", "description", LocalDate.now(), LocalTime.now(), LocalTime.now(), "category",
                "individual", "creatorName");

        testRestTemplate.postForObject(url, opportunity, Opportunity.class);

        verify(service).save(Mockito.any(Opportunity.class));

    }

    @Test
    void getOne() {
        String name = "help";

        testRestTemplate.getForObject(url + "/" + name, Opportunity.class);

        verify(service).get(name);
    }

    @Test
    void getAll() {
        testRestTemplate.getForObject(url, Opportunity[].class);

        verify(service).getAll();
    }
}
