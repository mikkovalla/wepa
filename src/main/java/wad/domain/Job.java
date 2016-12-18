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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author mikko
 */
@Entity
@Table(name = "Jobs")
public class Job extends AbstractPersistable<Long>{

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Categories cat;

    @ManyToOne
    @JoinColumn(name = "employerId")
    private Employer emp;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private Types type;

    @NotBlank
    @Length(min = 3)
    @NotNull
    private String jobName;

    @NotBlank
    @Length(min = 10)
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private String area;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany
    private List<EmployeeJobsApply> employeeJobsApply;

    public Job() {
    }

    public Job(Categories cat, Employer emp, Types type, String jobName, String description, String area, Date created) {
        this.cat = cat;
        this.emp = emp;
        this.type = type;
        this.jobName = jobName;
        this.description = description;
        this.area = area;
        this.created = created;
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
