package com.project.apptruistic.security;

import com.project.apptruistic.persistence.domain.Organization;
import com.project.apptruistic.persistence.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Organization organization = organizationRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Organization not found with email: " + email));
        return UserDetailsImpl.build(organization);
    }
}
