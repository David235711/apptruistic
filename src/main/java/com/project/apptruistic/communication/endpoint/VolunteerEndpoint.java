package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.VolunteerService;
import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerEndpoint {

    private final VolunteerService volunteerService;

    public VolunteerEndpoint(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    Volunteer post(@Valid @RequestBody Volunteer volunteer) {
        return volunteerService.save(volunteer);
    }
    @GetMapping()
    List<Volunteer> getAll() {
        return volunteerService.getAll();
    }

}
