package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggested")
public class SuggestedOpportunityEndpoint {

    private final OpportunityService opportunityService;

    public SuggestedOpportunityEndpoint(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GetMapping("/{id}")
    List<Opportunity> getSuggested(@PathVariable String id) {
        return opportunityService.findSuggestedOpportunities(id);
    }
}
