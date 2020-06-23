package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.OpportunityCategory;
import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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


    @GetMapping("/time/{time}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getTime(@PathVariable String time) {
        List<Opportunity> opportunities = opportunityService.getAllByTime(time);
        return opportunities;
    }

    @GetMapping("/date/{date}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    List<Opportunity> getDate(@PathVariable String date) {
        List<Opportunity> opportunities = opportunityService.getAllByOccurDate(date);
        return opportunities;
    }

/*
    @RequestMapping(method = RequestMethod.GET, value = "/custom")
    public String controllerMethod(@RequestParam Map<String, String> customQuery) {

        System.out.println("customQuery = zipcode " + customQuery.containsKey("zipcode"));
        System.out.println("customQuery = time " + customQuery.containsKey("time"));
        return customQuery.toString();
    }


    @RequestMapping(value = "/mno/{objectKey}", method = RequestMethod.GET, produces = "application/json")
    public List<String> getBook(HttpServletRequest httpServletRequest, @PathVariable(name = "objectKey") String objectKey
            , @RequestParam(value = "id", defaultValue = "false")String id, @RequestParam(value = "name", defaultValue = "false") String name) throws Exception {
     return List.of();
    }

 */


    @GetMapping("/search")
    @ResponseBody
    public List<Opportunity> processSearch(@RequestParam String creatorName,
                                           @RequestParam int zipcode,
                                           @RequestParam(required = false) String time,
                                           @RequestParam(required = false) String date

    ) {
        List<Opportunity> nameOpportunities = opportunityService.getAllByOrganizationName(creatorName);
        List<Opportunity> zipOpportunities = opportunityService.getAllByZipCode(zipcode);
      if( nameOpportunities.containsAll(zipOpportunities)) {

          return nameOpportunities;
      }
      return List.of();

        /*
        return nameOpportunities.stream()
                .filter(e -> e.getZipCode() == zipcode && opportunityService.getTimeCategory(e.getStartTime()).equals(time)
                        && opportunityService.convertStringToLOcalDate(date).equals(e.getOccurDate()))
                .collect(Collectors.toList());

         */
    }


    @GetMapping("/searchtest")
    @ResponseBody
    public String processSearch1(@RequestParam String creatorName,
                                 @RequestParam int zipcode) {
        return creatorName + zipcode;

    }


}









