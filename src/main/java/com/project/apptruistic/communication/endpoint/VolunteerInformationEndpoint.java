package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.communication.endpoint.dto.VolunteerDTO;
import com.project.apptruistic.logic.VolunteerService;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/volunteerinformation")
public class VolunteerInformationEndpoint {

    private final VolunteerService volunteerService;

    public VolunteerInformationEndpoint(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    Volunteer getVolunteerByEmail(@PathVariable String email) {
        System.out.println("volunteerinformation request received");
        return volunteerService.getVolunteerByEmail(email).orElse(null);
    }

    @PutMapping("/edit/{email}")
    @PreAuthorize("hasRole('VOLUNTEER')")
    Volunteer modifyVolunteer(@PathVariable String email, @Valid @RequestBody Volunteer volunteer) {
        return volunteerService.editVolunteer(email, volunteer).orElse(null);
    }

    @GetMapping("/preview/{id}")
    VolunteerDTO getVolunteerDTO(@PathVariable String id) {
        return volunteerService.getVolunteerDtoById(id).orElse(null);
    }
}
