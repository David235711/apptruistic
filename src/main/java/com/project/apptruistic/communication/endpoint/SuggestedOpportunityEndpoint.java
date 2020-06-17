package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/suggested")
public class SuggestedOpportunityEndpoint {

    private final OpportunityService opportunityService;

    public SuggestedOpportunityEndpoint(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getSuggested(@PathVariable String id) {
        return opportunityService.findSuggestedOpportunities(id);
    }
}
