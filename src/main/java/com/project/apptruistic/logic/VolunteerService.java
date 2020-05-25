package com.project.apptruistic.logic;

import com.project.apptruistic.persistence.domain.Volunteer;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {

    private final VolunteerRepository repository;

    public VolunteerService(VolunteerRepository repository) {
        this.repository = repository;
    }
    public Volunteer save(Volunteer volunteer){
        return repository.save(volunteer);
    }
}
