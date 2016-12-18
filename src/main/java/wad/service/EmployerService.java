/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        // demo content
        employerRepo.save(new Employer("JavaGurut", "java@java.com", "javaguru", "javaguru", "javagurut on hieno lafka", new Date()));
        employerRepo.save(new Employer("Osaajat", "osaajat@osaajat.com", "osaajat", "osaajat", "osaajat on hieno lafka", new Date()));
        employerRepo.save(new Employer("KoodinVääntäjät", "css@css.com", "csshtml", "csshtml", "jKoodinvääntäjät on hieno lafka joka osaa", new Date()));
    }

    public Employer findOne(Long id) {
        return employerRepo.findOne(id);
    }

    public List<Employer> all() {
        return employerRepo.findAll();
    }

    public Employer update(Employer employer) {
        return employerRepo.save(employer);
    }

    public void delete(Long id) {
        employerRepo.delete(employerRepo.findOne(id));
    }

    public Employer addEmployer(Employer employer) {
        Employer emp = new Employer(
                employer.getCompanyName(),
                employer.getEmail(),
                employer.getUsername(),
                employer.getPassword(),
                employer.getCompanyDescription(),
                new Date());
        return employerRepo.save(emp);
    }
}
