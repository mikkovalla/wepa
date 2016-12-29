/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author mikko
 */
@Entity
public class EmployeeJobsApply extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn
    private Job job;

    @ManyToOne
    @JoinColumn
    private Employee employee;

    public EmployeeJobsApply() {
    }

    public EmployeeJobsApply(Job job, Employee employee) {
        this.job = job;
        this.employee = employee;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
