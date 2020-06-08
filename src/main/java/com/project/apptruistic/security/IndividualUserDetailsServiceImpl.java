package com.project.apptruistic.security;

import com.project.apptruistic.persistence.domain.Individual;

import com.project.apptruistic.persistence.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndividualUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IndividualRepository individualRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Individual individual = individualRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Individual not found with email: " + email));
        return UserDetailsImpl.build(individual);
    }
}
