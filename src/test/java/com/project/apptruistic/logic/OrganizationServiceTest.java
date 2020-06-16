package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Organization;
import com.project.apptruistic.persistence.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
/*
@SpringBootTest(webEnvironment = NONE)
class OrganizationServiceTest {
    @Autowired
    OrganizationService organizationService;

    @MockBean
    OrganizationRepository organizationRepository;

    @Test
    void friendFindsBothPersons() {
        String email = "nobody@gmail.com";

        Organization organization = new Organization("Caritas", "name", "family name", "nobody@gmail.com",
                "1234", "password", "donau Straße", "1", "Vienna", 1010);

        when(organizationRepository.findOneByEmail(email))
                .thenReturn(Optional.of(organization));

        organizationService.edit(email);

        verify(organizationRepository).findOneByEmail(email);


    }
    }


 */
