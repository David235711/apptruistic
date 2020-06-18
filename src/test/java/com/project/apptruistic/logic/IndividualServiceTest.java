package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Individual;
import com.project.apptruistic.persistence.repository.IndividualRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndividualServiceTest {

    @Autowired
    IndividualService individualService;

    @MockBean
    IndividualRepository individualRepository;

    @MockBean
    PasswordEncoder encoder;

    @Test
    void getIndividualByEmail() {
        String email = "email";
        individualService.getIndividualByEmail(email);

        verify(individualRepository).findOneByEmail(email);
    }

//    @Test
//    void saveFindsIndividual() {
//        String email = "email";
//        Individual individual = new Individual("firstName", "lastName", email, "phone", LocalDate.now(), "gender", )
//        when(individualRepository.findOneByEmail(email))
//                .thenReturn(Optional.of(individual));
//    }

    @Test
    void editIndividual() {
    }
}