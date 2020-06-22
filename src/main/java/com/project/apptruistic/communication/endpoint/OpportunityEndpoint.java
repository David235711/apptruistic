package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityCategory;
import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @GetMapping("/id/{id}")
    Opportunity getById(@PathVariable String id) {
        return opportunityService.getById(id).orElse(null);
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


    @GetMapping("/zipcode/{zipcode}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getByZipcode(@PathVariable int zipcode) {
        List<Opportunity> opportunities = opportunityService.getAllByZipCode(zipcode);
        return opportunities;
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getByCategory(@PathVariable OpportunityCategory category) {
        List<Opportunity> opportunities = opportunityService.getAllByCategory(category);
        return opportunities;
    }


    @GetMapping("/organization/{organizationName}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getByOrganizationName(@PathVariable String organizationName) {
        List<Opportunity> opportunities = opportunityService.getAllByOrganizationName(organizationName);
        return opportunities;
    }

    @GetMapping("/singleOpportunities")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getSingleOpportuities() {
        List<Opportunity> opportunities = opportunityService.getAllSingleOpportunities();
        return opportunities;
    }

    @GetMapping("/groupOpportunities")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getGroupOpportuities() {
        List<Opportunity> opportunities = opportunityService.getAllGroupOpportunities();
        return opportunities;
    }

    @GetMapping("/individualCreator")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getIndividualCreator() {
        List<Opportunity> opportunities = opportunityService.getAllByIndividualCreator();
        return opportunities;
    }
    @GetMapping("/organizationCreator")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getOrganizationCreator() {
        List<Opportunity> opportunities = opportunityService.getAllByOrganizationCreator();
        return opportunities;
    }

/*
    @GetMapping("/time/{time}")
     List<Opportunity> getTime(@PathVariable String time) {
        List<Opportunity> opportunities = opportunityService.getAllByTime(time);
        return opportunities;
    }

 */

}
