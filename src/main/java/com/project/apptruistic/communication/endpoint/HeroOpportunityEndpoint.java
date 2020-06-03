package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hero")
public class HeroOpportunityEndpoint {

    private final OpportunityService opportunityService;

    public HeroOpportunityEndpoint(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }
    @GetMapping
    List<Opportunity> getHeroes(){
        return opportunityService.findHeroOpportunities();
    }
}
