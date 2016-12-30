/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Employer;
import wad.repository.EmployerRepository;

/**
 *
 * @author mikko
 */
@Service
public class EmployerService {

    @Autowired
    EmployerRepository employerRepo;

    @Autowired
    private LoggedInEmployerService employerLoggedIn;

    public Employer findOne(Long id) {
        return employerRepo.findOne(id);
    }

    public Employer findByUsername(String username) {
        return employerRepo.findByUsername(username);
    }

    public Employer findByEmail(String email) {
        return employerRepo.findByEmail(email);
    }

    public Employer findByPassword(String password) {
        return employerRepo.findByEmail(password);
    }

    public List<Employer> all() {
        return employerRepo.findAll();
    }

    public Employer update(Employer employer) {
        return employerRepo.save(employer);
    }

    public void delete(Employer employer) {
        employerRepo.delete(employerRepo.findOne(employer.getId()));
    }

    public Employer addEmployer(Employer employer) {
        return employerRepo.save(employer);
    }

    public Employer loggedIn() {
        return employerLoggedIn.getEmployerLoggedIn();
    }

    public Long getLoggedInId() {
        return employerRepo.findByUsername(employerLoggedIn.getEmployerLoggedIn().getUsername()).getId();
    }
}
