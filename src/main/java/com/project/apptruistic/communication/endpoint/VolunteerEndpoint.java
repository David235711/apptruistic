package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.VolunteerService;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/volunteerinformation")
public class VolunteerEndpoint {

    private final VolunteerService volunteerService;

    public VolunteerEndpoint(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    Volunteer getVolunteerByEmail(@PathVariable String email) {
        System.out.println("volunteerinformation request received");
        return volunteerService.getVolunteerByEmail(email).orElse(null);
    }

}
