package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Individual;
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
}
