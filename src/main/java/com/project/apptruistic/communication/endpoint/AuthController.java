package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.persistence.domain.*;
import com.project.apptruistic.persistence.repository.IndividualRepository;
import com.project.apptruistic.persistence.repository.OrganizationRepository;
import com.project.apptruistic.persistence.repository.RoleRepository;
import com.project.apptruistic.persistence.repository.VolunteerRepository;
import com.project.apptruistic.security.JwtUtils;
import com.project.apptruistic.security.UserDetailsImpl;
import com.project.apptruistic.security.payload.request.IndividualSignupRequest;
import com.project.apptruistic.security.payload.request.LoginRequest;
import com.project.apptruistic.security.payload.request.OrganizationSignupRequest;
import com.project.apptruistic.security.payload.request.VolunteerSignupRequest;
import com.project.apptruistic.security.payload.response.JwtResponse;
import com.project.apptruistic.security.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    IndividualRepository individualRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("login request received");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/register/volunteer")
    public ResponseEntity<?> registerUser(@Valid @RequestBody VolunteerSignupRequest signUpRequest) {
        System.out.println("volunteer request received");
        if (volunteerRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new volunteer's account
        Volunteer user = new Volunteer(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getDateOfBirth(),
                signUpRequest.getGender(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(),
                signUpRequest.getCategories()
                );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_VOLUNTEER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "individual":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_INDIVIDUAL)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "organization":
                        Role modRole = roleRepository.findByName(ERole.ROLE_ORGANIZATION)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_VOLUNTEER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user.getCategories().add("social"); //ToDo: remove default category after frontend implementatiion
        volunteerRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Volunteer registered successfully!"));
    }

    @PostMapping("/register/individual")
    public ResponseEntity<?> registerIndividual(@Valid @RequestBody IndividualSignupRequest signUpRequest) {
        System.out.println("individual request received");
        if (individualRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new individual's account
        Individual individual = new Individual(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                signUpRequest.getDateOfBirth(),
                signUpRequest.getGender(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getStreet(),
                signUpRequest.getHouseNumber(),
                signUpRequest.getCity(),
                signUpRequest.getZipCode()
        );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_INDIVIDUAL)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "individual":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_INDIVIDUAL)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "organization":
                        Role modRole = roleRepository.findByName(ERole.ROLE_ORGANIZATION)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_VOLUNTEER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        individual.setRoles(roles);
        individualRepository.save(individual);

        return ResponseEntity.ok(new MessageResponse("Individual registered successfully!"));
    }

    @PostMapping("/register/organization")
    public ResponseEntity<?> registerOrganization(@Valid @RequestBody OrganizationSignupRequest signUpRequest) {
        System.out.println("organization request received");
        if (organizationRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new organization's account
        Organization organization = new Organization(
                signUpRequest.getOrganizationName(),
                signUpRequest.getContactFirstName(),
                signUpRequest.getContactLastName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getStreet(),
                signUpRequest.getHouseNumber(),
                signUpRequest.getCity(),
                signUpRequest.getZipCode()
        );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_ORGANIZATION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "individual":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_INDIVIDUAL)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "organization":
                        Role modRole = roleRepository.findByName(ERole.ROLE_ORGANIZATION)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_VOLUNTEER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        organization.setRoles(roles);
        organizationRepository.save(organization);

        return ResponseEntity.ok(new MessageResponse("Organization registered successfully!"));
    }
}
