package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/hero")
public class HeroOpportunityEndpoint {

    private final OpportunityService opportunityService;

    public HeroOpportunityEndpoint(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GetMapping
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('INDIVIDUAL') or hasRole('ORGANIZATION')")
    List<Opportunity> getHeroes() {
        return opportunityService.findHeroOpportunities();
    }
}
