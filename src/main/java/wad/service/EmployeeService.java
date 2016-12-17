/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Employee;
import wad.repository.EmployeeRepository;

/**
 *
 * @author mikko
 */
@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepo;
    
    public Employee findOne(Long id) {
        return employeeRepo.findOne(id);
    }
    
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }
    
    public Employee update(Employee employee) {
        return employeeRepo.save(employee);
    }
    
    public void delete(Long id) {
        employeeRepo.delete(employeeRepo.findOne(id));
    }
}
