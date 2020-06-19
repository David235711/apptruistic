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

import static org.mockito.Mockito.*;
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

    @Test
    void saveFindsIndividual() {
        String email = "email";
        Individual individual = new Individual("firstName", "lastName", email, "phone", LocalDate.now(), "gender", "password", "street", "houseNumber", "city", 1000);
        when(individualRepository.findOneByEmail(email))
                .thenReturn(Optional.of(individual));

        individualService.save(individual);

        verify(individualRepository).findOneByEmail(email);
        verifyNoInteractions(encoder);
        verifyNoMoreInteractions(individualRepository);
    }

    @Test
    void saveDoesNotFindIndividual() {
        String email = "email";
        String password = "password";
        Individual individual = new Individual("firstName", "lastName", email, "phone", LocalDate.now(), "gender", password, "street", "houseNumber", "city", 1000);
        when(individualRepository.findOneByEmail(email))
                .thenReturn(Optional.empty());
        when(encoder.encode(password))
                .thenReturn(password);

        individualService.save(individual);

        verify(individualRepository).findOneByEmail(email);
        verify(encoder).encode(password);
        verify(individualRepository).save(individual);
    }

    @Test
    void editDoesNotFindIndividual() {
        String email = "email";
        String password = "password";
        Individual individual = new Individual("firstName", "lastName", email, "phone", LocalDate.now(), "gender", password, "street", "houseNumber", "city", 1000);
        when(individualRepository.findOneByEmail(email))
                .thenReturn(Optional.empty());

        individualService.editIndividual(email, individual);

        verify(individualRepository).findOneByEmail(email);
        verifyNoInteractions(encoder);
        verifyNoMoreInteractions(individualRepository);
    }

    @Test
    void editFindsIndividual() {
        String email = "email";
        String password = "password";
        Individual individual = new Individual("firstName", "lastName", email, "phone", LocalDate.now(), "gender", password, "street", "houseNumber", "city", 1000);
        when(individualRepository.findOneByEmail(email))
                .thenReturn(Optional.of(individual));
        when(encoder.encode(password))
                .thenReturn(password);

        individualService.editIndividual(email, individual);

        verify(individualRepository).findOneByEmail(email);
        verify(individualRepository).save(individual);
    }
}