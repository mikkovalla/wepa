/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author mikko
 */
@Entity
public class Job extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn
    private Categories cat;

    @ManyToOne
    @JoinColumn
    private Employer emp;

    @ManyToOne
    @JoinColumn
    private Types type;

    @NotBlank(message = "Anna duunin nimi")
    @Length(min = 3, message = "nimen on oltava vähintään 3 merkkiä pitkä!")
    private String jobName;

    @NotBlank(message = "Anna duunin kuvaus!")
    @Length(min = 10, message = "duunin kuvaus on oltava vähintään 10 merkkiä!")
    private String description;

    @NotBlank(message = "anna alue missä duuni on!")
    private String area;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany(mappedBy = "job")
    private List<EmployeeJobsApply> employeeJobsApply;

    public Job() {
    }

    public Job(Categories cat, Employer emp, Types type, String jobName, String description, String area) {
        this.cat = cat;
        this.emp = emp;
        this.type = type;
        this.jobName = jobName;
        this.description = description;
        this.area = area;
        this.created = new Date();
    }

    public Categories getCategory() {
        return cat;
    }

    public void setCategory(Categories cat) {
        this.cat = cat;
    }

    public Employer getEmployer() {
        return emp;
    }

    public void setEmployer(Employer emp) {
        this.emp = emp;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<EmployeeJobsApply> getEmployeeJobsApply() {
        if (this.employeeJobsApply == null) {
            this.employeeJobsApply = new ArrayList<>();
        }
        return employeeJobsApply;
    }

    public void setEmployeeJobsApply(List<EmployeeJobsApply> employeeJobsApply) {
        this.employeeJobsApply = employeeJobsApply;
    }
}
