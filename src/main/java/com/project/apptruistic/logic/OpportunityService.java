package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final int oneWeek;

    public OpportunityService(OpportunityRepository opportunityRepository,
                              @Value("${apptruistic.oneWeek}") int oneWeek) {
        this.opportunityRepository = opportunityRepository;
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
                .collect(Collectors.toList());
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

    public int calculateNumberOfNeededVolunteers(Opportunity opportunity){
        return opportunity.getNumberOfParticipants()-opportunity.getAcceptedVolunteers().size();
    }
}
