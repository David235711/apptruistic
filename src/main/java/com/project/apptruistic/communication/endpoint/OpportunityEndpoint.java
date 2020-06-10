package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityCategory;
import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/opportunities")
public class OpportunityEndpoint {
    private final OpportunityService opportunityService;

    public OpportunityEndpoint(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @PostMapping
    @PreAuthorize("hasRole('INDIVIDUAL') or hasRole('ORGANIZATION')")
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

    @GetMapping("/availables")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> get() {
        return opportunityService.getAllAvailables();
    }

    @GetMapping("/categories")
//    @PreAuthorize("hasRole('INDIVIDUAL') or hasRole('ORGANIZATION') or hasRole('VOLUNTEER')")
    OpportunityCategory[] getAllCategories() {
        return OpportunityCategory.values();
    }

}
