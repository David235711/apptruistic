package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.logic.VolunteerService;
import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/volunteers")
public class VolunteerEndpoint {

    private final VolunteerService volunteerService;

    public VolunteerEndpoint(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    ResponseEntity<String>  post(@Valid @RequestBody Volunteer volunteer) {
        volunteerService.save(volunteer);
        return ResponseEntity.ok("User is valid");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            UnexpectedTypeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(String.valueOf(ex.getCause()), ex.getMessage());
        return errors;
    }
}
