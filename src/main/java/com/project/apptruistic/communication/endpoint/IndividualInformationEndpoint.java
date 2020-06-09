package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.IndividualService;
import com.project.apptruistic.persistence.domain.Individual;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/individualinformation")
public class IndividualInformationEndpoint {

    private final IndividualService individualService;

    public IndividualInformationEndpoint(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('INDIVIDUAL')")
    Individual getVolunteerByEmail(@PathVariable String email) {
        System.out.println("individualinformation request received");
        return individualService.getIndividualByEmail(email).orElse(null);
    }

}
