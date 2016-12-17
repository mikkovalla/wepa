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
}
