package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/opportunities")
public class OpportunityEndpoint {
    private final OpportunityService opportunityService;


    public OpportunityEndpoint(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    // @Secured({"ROLE_INDIVIDUAL", "ROLE_ORGANISATION"})
    @PostMapping
    Opportunity post(@Valid @RequestBody Opportunity opportunity) {
        return opportunityService.save(opportunity);
    }

    @GetMapping("/{name}")
    Opportunity get(@PathVariable String name) {
        return opportunityService.get(name)
                .orElse(null);
    }

    @GetMapping()
    List<Opportunity> getAll() {
        return opportunityService.getAll();
    }
}
