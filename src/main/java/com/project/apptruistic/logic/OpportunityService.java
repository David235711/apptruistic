package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Creator;
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
        boolean isAvailable = exist(opportunity);
        if (isAvailable) {
            return opportunity;
        }
        return createOpportunity(opportunity);
    }

    Boolean exist(Opportunity opportunity) {
        Optional<Opportunity> oOpportunity = opportunityRepository.findOneByName(opportunity.getName());
        if (oOpportunity.isPresent()) {
            if (oOpportunity.get().getCreator().equals(opportunity.getCreator()) &&
                    oOpportunity.get().getOccurDate().equals(opportunity.getOccurDate()) &&
                    oOpportunity.get().getStartTime().equals(opportunity.getStartTime()) &&
                    oOpportunity.get().getEndTime().equals(opportunity.getEndTime())) ;
            return true;
        }
        return false;
    }

    private Opportunity createOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }


    public Optional<Opportunity> get(String name) {
        Optional<Opportunity> oOpportunity = opportunityRepository.findOneByName(name);
        return oOpportunity;
    }

    public List<Opportunity> getAll() {
        return opportunityRepository.findAll();
    }
}
