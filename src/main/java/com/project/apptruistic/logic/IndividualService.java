package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Individual;
import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.IndividualRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndividualService {
    private final IndividualRepository repository;
    private final PasswordEncoder passwordEncoder;

    public IndividualService(IndividualRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Individual> getIndividualByEmail(String email) {
        return repository.findOneByEmail(email);
    }

    public Individual save(Individual individual) {
        return repository.findOneByEmail(individual.getEmail())
                .orElseGet(() -> createIndividual(individual));
    }

    private Individual createIndividual(Individual individual) {
        System.out.println("oopsi, createIndividual in IndividualService got called!");
        String password = individual.getPassword();
        String encoded = passwordEncoder.encode(password);
        individual.setPassword(encoded);
        return repository.save(individual);
    }
    public Optional<Individual> editIndividual(String email, Individual newIndividual) {
        Optional<Individual> oIndividual = repository.findOneByEmail(email);
        if (oIndividual.isEmpty()) {
            System.out.println("modify individual not found");
            return Optional.empty();
        }
        Individual individual = oIndividual.get();
        individual.setFirstName(newIndividual.getFirstName());
        individual.setLastName(newIndividual.getLastName());
        individual.setPhoneNumber(newIndividual.getPhoneNumber());
        individual.setDateOfBirth(newIndividual.getDateOfBirth());
        individual.setGender(newIndividual.getGender());
        String password = individual.getPassword();
        String encoded = passwordEncoder.encode(password);
        individual.setPassword(encoded);
        individual.setStreet(newIndividual.getStreet());
        individual.setHouseNumber(newIndividual.getHouseNumber());
        individual.setCity(newIndividual.getCity());
        individual.setZipCode(newIndividual.getZipCode());
        individual.setPersonalDescription(newIndividual.getPersonalDescription());
        repository.save(individual);
        return Optional.of(individual);
    }
}
