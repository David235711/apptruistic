package com.project.apptruistic.persistence.validation;

import com.project.apptruistic.persistence.domain.Volunteer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VolunteerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Volunteer.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Volunteer volunteer = (Volunteer) obj;
        if (checkInputString(volunteer.getFirstName())) {
            errors.rejectValue("firstName", "firstName.empty");
        }

        if (checkInputString(volunteer.getLastName())) {
            errors.rejectValue("lastName", "lastName.empty");
        }

        if (checkInputLength(volunteer.getPassword())) {
            errors.rejectValue("password", "password.min");
        }

        if (checkInputString(volunteer.getEmail())) {
            errors.rejectValue("email", "email.empty");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

    private boolean checkInputLength(String input) {
        return (input == null || input.length() < 8);
    }
}