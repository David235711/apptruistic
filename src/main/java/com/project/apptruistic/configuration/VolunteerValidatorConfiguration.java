package com.project.apptruistic.configuration;

import com.project.apptruistic.persistence.validation.VolunteerValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VolunteerValidatorConfiguration {
    @Bean
    public VolunteerValidator volunteerValidator() {
        return new VolunteerValidator();
    }
}
