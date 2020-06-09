package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OrganizationService;
import com.project.apptruistic.persistence.domain.Organization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizationinformation")
public class OrganizationInformationEndpoint {

    private final OrganizationService organizationService;

    public OrganizationInformationEndpoint(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ORGANIZATION')")
    Organization getOrganizationByEmail(@PathVariable String email) {
        System.out.println("organizationinformation request received");
        return organizationService.getOrganizationByEmail(email).orElse(null);
    }

}
