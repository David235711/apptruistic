package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.IndividualService;
import com.project.apptruistic.persistence.domain.Individual;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PutMapping("/{email}/edit")
    @PreAuthorize("hasRole('INDIVIDUAL')")
    void modifyIndividual(@PathVariable String email, @Valid @RequestBody Individual individual){
        individualService.modifyIndividual(email, individual);
    }

}
