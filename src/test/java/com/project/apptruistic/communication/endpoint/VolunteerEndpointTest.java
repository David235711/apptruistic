package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.VolunteerService;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDate;
import java.util.Set;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class VolunteerEndpointTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    VolunteerService service;

    String url = "/volunteers";


    @ParameterizedTest
    @CsvSource({
            "'', lastName, password, email@com.com",
            "firstName, '', password, email@com.com",
            "firstName, lastName, wrong, email@com.com",
            "firstName, lastName, password, ''",
            "firstName, lastName, password, notEmail",

    })
    void postIsInvalid(String firstName, String lastName, String password, String email) {
        Volunteer volunteer = new Volunteer(firstName, lastName, LocalDate.now(), "gender", password, email, Set.of("ROLE_VOLUNTEER"), Set.of("category"));

        testRestTemplate.postForObject(url, volunteer, Volunteer.class);

        verify(service, never()).save(volunteer);
    }

    @Test
    void postIsValid() {
        Volunteer volunteer = new Volunteer("firstName", "lastName", LocalDate.now(), "gender", "password", "email@com.com", Set.of("ROLE_VOLUNTEER"), Set.of("category"));

        testRestTemplate.postForObject(url, volunteer, Volunteer.class);

        verify(service).save(volunteer);
    }
}