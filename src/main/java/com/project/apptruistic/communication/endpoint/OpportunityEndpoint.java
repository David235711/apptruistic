package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.CreatorType;
import com.project.apptruistic.logic.OpportunityCategory;
import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.repository.DynamicQuery;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/opportunities")
public class OpportunityEndpoint {
    private final OpportunityService opportunityService;
    private final OpportunityRepository opportunityRepository;

    public OpportunityEndpoint(OpportunityService opportunityService, OpportunityRepository opportunityRepository) {
        this.opportunityService = opportunityService;
        this.opportunityRepository = opportunityRepository;
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

    @GetMapping("/lookup")
    @ResponseBody
    public List<Opportunity> processSearch(
            @RequestParam int zipCode,
            @RequestParam (required = false) OpportunityCategory category,
            @RequestParam (required = false) String creatorName,
            @RequestParam int numberOfParticipants,
            @RequestParam (required = false) CreatorType creatorType,
            @RequestParam (required = true) boolean done,
            @RequestParam (required = false) String startTime,
            @RequestParam (required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate occurDate
            ) {

        if (zipCode == 0  && category == null && creatorName.isBlank() && numberOfParticipants == 0 && creatorType == null && startTime.isBlank() && occurDate == null) {
            return opportunityService.getAllAvailables();
        }
        DynamicQuery dynamicQuery = new DynamicQuery();
        dynamicQuery.setZipCode(zipCode);
        dynamicQuery.setCategory(category);
        dynamicQuery.setCreatorName(creatorName);
        dynamicQuery.setNumberOfParticipants(numberOfParticipants);
        dynamicQuery.setCreatorType(creatorType);
        dynamicQuery.setDone(done);
        dynamicQuery.setStartTime(startTime);
        dynamicQuery.setOccurDate(occurDate);
        System.out.println(dynamicQuery);
        return opportunityRepository.query(dynamicQuery);
    }

    @GetMapping("/organizations")
    //  @PreAuthorize("hasRole('VOLUNTEER')")
    Set<String> getAllOrganization() {
        Set<String> opportunities = opportunityService.getAllByOrganizationCreator();
        return opportunities;
    }
}
