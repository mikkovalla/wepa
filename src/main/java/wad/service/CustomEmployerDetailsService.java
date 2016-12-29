/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import wad.domain.Employer;
import wad.repository.EmployerRepository;

/**
 *
 * @author Mikko
 */
@Service
public class CustomEmployerDetailsService implements UserDetailsService {

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employer employer = employerRepository.findByUsername(username);

        if (employer == null) {
            throw new UsernameNotFoundException("Käyttäjä ei löydy: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                employer.getUsername(),
                employer.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("EMPLOYER")));
    }

}
