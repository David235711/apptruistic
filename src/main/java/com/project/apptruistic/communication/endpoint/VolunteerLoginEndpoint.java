package com.project.apptruistic.communication.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class VolunteerLoginEndpoint {

    private final String message;

    public VolunteerLoginEndpoint(
            @Value("${apptruistic.message}") String message) {
        this.message = message;
    }

    @PostMapping
    @Secured("ROLE_VOLUNTEER")
    String post() {
        return message;
    }
}
