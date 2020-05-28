package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public Opportunity save(Opportunity opportunity) {
        if (exists(opportunity)) {
            return opportunity;
        }
        return opportunityRepository.save(opportunity);
    }

    boolean exists(Opportunity opportunity) {
        Optional<Opportunity> oOpportunity = opportunityRepository.findOneByName(opportunity.getName());
        return oOpportunity.isPresent() && oOpportunity.get().getCreatorName().equals(opportunity.getCreatorName()) &&
                oOpportunity.get().getOccurDate().equals(opportunity.getOccurDate()) &&
                oOpportunity.get().getStartTime().equals(opportunity.getStartTime()) &&
                oOpportunity.get().getEndTime().equals(opportunity.getEndTime());
    }

    public Optional<Opportunity> get(String name) {
        return opportunityRepository.findOneByName(name);
    }

    public List<Opportunity> getAll() {
        return opportunityRepository.findAll();
    }
}
