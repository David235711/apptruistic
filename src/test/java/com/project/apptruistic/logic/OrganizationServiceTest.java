package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Organization;
import com.project.apptruistic.persistence.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class OrganizationServiceTest {

    @MockBean
    ApplicationRunner runner;

    @Autowired
    OrganizationService organizationService;

    @MockBean
    OrganizationRepository organizationRepository;

    @MockBean
    PasswordEncoder encoder;

    @Test
    void getOrganizationByEmail() {
        String email = "email";

        organizationService.getOrganizationByEmail(email);

        verify(organizationRepository).findOneByEmail(email);
    }

    @Test
    void saveFindsOrganization() {
        String email = "email";
        Organization organization = new Organization("name", "contactFirst", "contactLast", email, "number", "password", "street", "houseNum", "city", 1000);
        when(organizationRepository.findOneByEmail(email))
                .thenReturn(Optional.of(organization));

        organizationService.save(organization);

        verify(organizationRepository).findOneByEmail(email);
        verifyNoMoreInteractions(organizationRepository);
        verifyNoInteractions(encoder);
    }

    @Test
    void saveDoesNotFindOrganization() {
        String email = "email";
        String password = "password";
        Organization organization = new Organization("name", "contactFirst", "contactLast", email, "number", password, "street", "houseNum", "city", 1000);

        when(organizationRepository.findOneByEmail(email))
                .thenReturn(Optional.empty());
        when(encoder.encode(password))
                .thenReturn(password);

        organizationService.save(organization);

        verify(organizationRepository).findOneByEmail(email);
        verify(encoder).encode(password);
        verify(organizationRepository).save(organization);
    }

    @Test
    void editDoesNotFindOrganization() {
        String email = "email";
        String password = "password";
        Organization organization = new Organization("name", "contactFirst", "contactLast", email, "number", password, "street", "houseNum", "city", 1000);

        when(organizationRepository.findOneByEmail(email))
                .thenReturn(Optional.empty());

        organizationService.editOrganization(email, organization);

        verify(organizationRepository).findOneByEmail(email);
        verifyNoInteractions(encoder);
        verifyNoMoreInteractions(organizationRepository);
    }

    @Test
    void editFindsOrganization() {
        String email = "email";
        String password = "password";
        Organization organization = new Organization("name", "contactFirst", "contactLast", email, "number", password, "street", "houseNum", "city", 1000);

        when(organizationRepository.findOneByEmail(email))
                .thenReturn(Optional.of(organization));
        when(encoder.encode(password))
                .thenReturn(password);

        Organization newOrganization = new Organization("name", "contactFirst", "contactLast", email, "number", password, "street", "houseNum", "city", 1070);

        organizationService.editOrganization(email, newOrganization);

        verify(organizationRepository).findOneByEmail(email);
        verify(organizationRepository).save(newOrganization);
    }
}