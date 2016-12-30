/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.auth;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import wad.domain.Employer;
import wad.repository.EmployerRepository;

/**
 *
 * @author mikko
 */
@Component
public class JpaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private EmployerRepository employerRepo;


   @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getPrincipal().toString();
        String password = a.getCredentials().toString();
 
        Employer person = employerRepo.findByUsername(username);
 
        if (person == null) {
            throw new AuthenticationException("Unable to authenticate user " + username) {
            };
        }
 
        if (!BCrypt.hashpw(password, person.getSalt()).equals(person.getPassword())) {
            throw new AuthenticationException("Unable to authenticate user " + username) {
            };
        }
 
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("EMPLOYER"));
 
        return new UsernamePasswordAuthenticationToken(person.getUsername(), password, grantedAuths);
    }
 
    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
}
