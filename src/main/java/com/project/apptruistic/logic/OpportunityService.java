package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Individual;
import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.domain.Organization;
import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.IndividualRepository;
import com.project.apptruistic.persistence.repository.OpportunityRepository;
import com.project.apptruistic.persistence.repository.OrganizationRepository;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final VolunteerRepository volunteerRepository;
    private final OrganizationRepository organizationRepository;
    private final IndividualRepository individualRepository;
    private final OrganizationService organizationService;
    private final IndividualService individualService;
    private final int urgentLimitInWeeks;

    public OpportunityService(OpportunityRepository opportunityRepository,
                              VolunteerRepository volunteerRepository, OrganizationRepository organizationRepository, IndividualRepository individualRepository, OrganizationService organizationService, IndividualService individualService, @Value("${apptruistic.urgentLimitInWeeks}") int urgentLimitInWeeks) {
        this.opportunityRepository = opportunityRepository;
        this.volunteerRepository = volunteerRepository;
        this.organizationRepository = organizationRepository;
        this.individualRepository = individualRepository;
        this.organizationService = organizationService;
        this.individualService = individualService;
        this.urgentLimitInWeeks = urgentLimitInWeeks;
    }

    public Opportunity save(Opportunity opportunity) {
        int hashcode = opportunity.hashCode();
        Optional<Opportunity> oOpportunity = opportunityRepository.findOneByHashcode(hashcode);
        if (oOpportunity.isPresent()) {
            return oOpportunity.get();
        }
        opportunity.setHashcode(hashcode);
        calculateDuration(opportunity);
        opportunity.setTimestamp(LocalDateTime.now());
        opportunityRepository.save(opportunity);
        String creatorId = opportunity.getCreatorId();
        Optional<Organization> oOrganization = organizationRepository.findById(creatorId);
        Optional<Individual> oIndividual = individualRepository.findById(creatorId);
        if (oOrganization.isPresent()) {
            Organization organization = oOrganization.get();
            organization.getCreatedOpportunity().add(opportunity);
            organizationRepository.save(organization);
        }
        if (oIndividual.isPresent()) {
            Individual individual = oIndividual.get();
            individual.getCreatedOpportunity().add(opportunity);
            individualRepository.save(individual);
        }
        if (oOrganization.isEmpty() && oIndividual.isEmpty()) {
            System.out.println("creator not found");
        }
        return opportunity;
    }

    public Optional<Opportunity> get(String name) {
        return opportunityRepository.findOneByName(name);
    }

    public Optional<Opportunity> getById(String id) {
        return opportunityRepository.findById(id);
    }

    public List<Opportunity> getAll() {
        return opportunityRepository.findAll().stream()
                .sorted(Comparator.comparing(Opportunity::getTimestamp).reversed())
                .collect(toList());
    }


    public List<Opportunity> getAllAvailables() {
        return opportunityRepository.findAllByDoneFalse().stream()
                .sorted(Comparator.comparing(Opportunity::getTimestamp).reversed())
                .collect(toList());
    }

    public List<Opportunity> findHeroOpportunities() {
        return opportunityRepository.findAllByDoneFalse().stream()
                .filter(opportunity -> opportunity.getOccurDate().isBefore(LocalDate.now().plusWeeks(urgentLimitInWeeks)))
                .filter(opportunity -> !opportunity.getOccurDate().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(Opportunity::getTimestamp).reversed())
                .collect(toList());
    }

    public List<Opportunity> findSuggestedOpportunities(String id) {
        Optional<Volunteer> oVolunteer = volunteerRepository.findById(id);
        if (oVolunteer.isEmpty()) {
            return new ArrayList<>();
        }
        Volunteer volunteer = oVolunteer.get();
        return opportunityRepository.findAllByDoneFalse().stream()
                .filter(opportunity -> volunteer.getCategory().equals(opportunity.getCategory()))
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

    private void calculateDuration(Opportunity opportunity) {
        LocalTime startTime = opportunity.getStartTime();
        LocalTime endTime = opportunity.getEndTime();
        Duration duration = Duration.between(startTime, endTime);
        opportunity.setDurationInMinutes(duration.toMinutes());
    }


    public List<Opportunity> getAllByZipCode(int zipcode) {
        List<Opportunity> opportunities = opportunityRepository.findAllByZipCodeAndDoneFalse(zipcode);
        return opportunities;
    }

    public List<Opportunity> getAllByCategory(OpportunityCategory category) {
        List<Opportunity> opportunities = opportunityRepository.findAllByCategoryAndDoneFalse(category);
        return opportunities;
    }

    public List<Opportunity> getAllByOrganizationName(String organizationName) {
        return opportunityRepository.findAllByCreatorNameAndDoneFalse(organizationName).stream()
                .filter(e -> e.getCreatorType().equals(CreatorType.ORGANIZATION))
                .collect(Collectors.toList());

    }

    public List<Opportunity> getAllSingleOpportunities() {
        return opportunityRepository.findAllByDoneFalse().stream()
                .filter(e -> e.getNumberOfParticipants() == 1 )
                .collect(Collectors.toList());
    }

    public List<Opportunity> getAllGroupOpportunities() {
        return opportunityRepository.findAllByDoneFalse().stream()
                .filter(e -> e.getNumberOfParticipants() > 1)
                .collect(Collectors.toList());
    }

    public List<Opportunity> getAllByIndividualCreator() {
        List<Opportunity> opportunities = opportunityRepository.findAllByCreatorTypeAndDoneFalse(CreatorType.INDIVIDUAL);
        return opportunities;
    }

    public List<Opportunity> getAllByOrganizationCreator() {
        List<Opportunity> opportunities = opportunityRepository.findAllByCreatorTypeAndDoneFalse(CreatorType.ORGANIZATION);
        return opportunities;
    }



    public List<Opportunity> getAllByTime(String time) {
        List<Opportunity> opportunities = opportunityRepository.findAllByDoneFalse();
        //LocalTime morning = LocalTime.parse("12:00");
        LocalTime morning = LocalTime.NOON;
        LocalTime afternoon = LocalTime.parse("18:00");
        LocalTime night = LocalTime.parse("23:59");

        List<Opportunity> morningOpportunities = opportunities.stream()
                .filter(e -> e.getStartTime().isBefore(LocalTime.NOON))
                .collect(Collectors.toList());

        List<Opportunity> afternoonOpportunities = opportunities.stream()
                .filter(e -> e.getStartTime().isBefore(afternoon) && e.getStartTime().isAfter(morning))
                .collect(Collectors.toList());

        List<Opportunity> nightOpportunities = opportunities.stream()
                .filter(e -> e.getStartTime().isBefore(night) && e.getStartTime().isAfter(afternoon))
                .collect(Collectors.toList());

        if (time.equals("Morning")) {
            return morningOpportunities;
        }
        if (time.equals("Afternoon")) {
            return afternoonOpportunities;
        }

        return nightOpportunities;

    }

    public List<Opportunity> getAllByOccurDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate convertedDate = LocalDate.parse(date, formatter);
        List<Opportunity> opportunities = opportunityRepository.findAllByDoneFalse().stream()
                .filter(e -> e.getOccurDate().equals(convertedDate))
                .collect(Collectors.toList());

        return opportunities;
    }





    public String getTimeCategory(LocalTime time) {
        List<Opportunity> opportunities = opportunityRepository.findAllByDoneFalse();
        LocalTime morning = LocalTime.NOON;
        LocalTime afternoon = LocalTime.parse("18:00");
        LocalTime night = LocalTime.parse("23:59");

        if (time.isBefore(morning)) return "Morning";
        if (time.isBefore(afternoon) && time.isAfter(morning)) return "Afternoon";
        return "Night";
    }


    public LocalDate convertStringToLOcalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate convertedDate = LocalDate.parse(date, formatter);
        return convertedDate;
    }



}
