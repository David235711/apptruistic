package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.VolunteerService;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volunteer")
public class VolunteerEndpoint {

    private final VolunteerService volunteerService;

    public VolunteerEndpoint(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }
    @PostMapping
    Volunteer post(@RequestBody Volunteer volunteer){
         return volunteerService.save(volunteer);

    }
}