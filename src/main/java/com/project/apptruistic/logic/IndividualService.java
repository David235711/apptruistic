package com.project.apptruistic.logic;

import com.project.apptruistic.communication.dto.IndividualDTO;
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
        individual.setStreet(newIndividual.getStreet());
        individual.setHouseNumber(newIndividual.getHouseNumber());
        individual.setCity(newIndividual.getCity());
        individual.setZipCode(newIndividual.getZipCode());
        individual.setPersonalDescription(newIndividual.getPersonalDescription());
        String password = newIndividual.getPassword();
        if(!password.equals(individual.getPassword())){
            String encode = passwordEncoder.encode(password);
            individual.setPassword(encode);
            System.out.println("old password");
        }
        repository.save(individual);
        return Optional.of(individual);
    }

    public Optional<IndividualDTO> getIndividualDtoById(String id) {
        Optional<Individual> oIndividual = repository.findById(id);
        if (oIndividual.isEmpty()) {
            System.out.println("individual DTO not found");
            return Optional.empty();
        }
        Individual individual = oIndividual.get();
        IndividualDTO individualDTO = new IndividualDTO(
                individual.getId(),
                individual.getFirstName(),
                individual.getLastName(),
                individual.getGender(),
                individual.getZipCode(),
                individual.getPersonalDescription()
        );
        return Optional.of(individualDTO);
    }
}
