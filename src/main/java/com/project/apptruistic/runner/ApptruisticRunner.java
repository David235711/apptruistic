package com.project.apptruistic.runner;

import com.project.apptruistic.persistence.repository.OpportunityRepository;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApptruisticRunner {
    @Bean
    ApplicationRunner prepareStuff(OpportunityRepository opportunityRepository, VolunteerRepository volunteerRepository) {
        return args -> {
//            Optional<Opportunity> optionalOpportunity1 = opportunityRepository.findOneByHashcode(501632404);
//            Optional<Opportunity> optionalOpportunity2 = opportunityRepository.findOneByHashcode(-1063479800);
//            Optional<Opportunity> optionalOpportunity3 = opportunityRepository.findOneByHashcode(1032714727);
//            Optional<Volunteer> oVolunteer = volunteerRepository.findOneByEmail("gina@com");
//            if (oVolunteer.isPresent() && optionalOpportunity1.isPresent() && optionalOpportunity2.isPresent()
//                    && optionalOpportunity3.isPresent()) {
//                oVolunteer.get().getCategories().add("social");
//                oVolunteer.get().getAcceptedOpportunities().add(optionalOpportunity1.get());
//                oVolunteer.get().getAppliedOpportunities().add(optionalOpportunity2.get());
//                oVolunteer.get().getDeclinedOpportunities().add(optionalOpportunity3.get());
//                volunteerRepository.save(oVolunteer.get());
//            }
        };
    }
}
