package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private  final VolunteerRepository volunteerRepository;
    private final int oneWeek;

    public OpportunityService(OpportunityRepository opportunityRepository,
                              VolunteerRepository volunteerRepository, @Value("${apptruistic.oneWeek}") int oneWeek) {
        this.opportunityRepository = opportunityRepository;
        this.volunteerRepository = volunteerRepository;
        this.oneWeek = oneWeek;
    }

    public Opportunity save(Opportunity opportunity) {
        int hashcode = opportunity.hashCode();
        Optional<Opportunity> oOpportunity = opportunityRepository.findOneByHashcode(hashcode);
        if (oOpportunity.isPresent()) {
            return oOpportunity.get();
        }
        opportunity.setHashcode(hashcode);

        return opportunityRepository.save(opportunity);
    }

    public Optional<Opportunity> get(String name) {
        return opportunityRepository.findOneByName(name);
    }

    public List<Opportunity> getAll() {
        return opportunityRepository.findAll();
    }


    public List<Opportunity> getAllAvailables() {
        return opportunityRepository.findAllByDoneFalse();
    }

    public List<Opportunity> findHeroOpportunities() {
        return opportunityRepository.findAllByDoneFalse().stream()
                .filter(opportunity -> opportunity.getOccurDate().isBefore(LocalDate.now().plusWeeks(oneWeek)))
                .filter(opportunity -> !opportunity.getOccurDate().isBefore(LocalDate.now()))
                .collect(toList());
    }

    public List<Opportunity> findSuggestedOpportunities(String id) {
        Optional<Volunteer> oVolunteer = volunteerRepository.findById(id);
        if(oVolunteer.isEmpty()){
            return new ArrayList<>();
        }
        Volunteer volunteer =oVolunteer.get();
        return opportunityRepository.findAllByDoneFalse().stream()
                .filter(opportunity -> volunteer.getCategories().contains(opportunity.getCategory()))
                .collect(toList());
    }

    public Optional<Opportunity> markAsDone(String id) {
        Optional<Opportunity> oOpportunity = opportunityRepository.findById(id);
        if (oOpportunity.isEmpty()) {
            return Optional.empty();
        }
        Opportunity opportunity = oOpportunity.get();
        opportunity.setDone(true);
        opportunityRepository.save(opportunity);
        return Optional.of(opportunity);
    }


}
