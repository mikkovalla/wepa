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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    private Categories category;

    @ManyToOne
    @JoinColumn
    private Employer emp;

    @ManyToOne
    @JoinColumn
    private Types type;

    @ManyToMany(mappedBy = "jobs")
    private List<Area> areas;

    @NotBlank(message = "Anna duunin nimi")
    @Length(min = 3, message = "nimen on oltava vähintään 3 merkkiä pitkä!")
    private String jobName;

    @NotBlank(message = "Anna duunin kuvaus!")
    @Length(min = 10, message = "duunin kuvaus on oltava vähintään 10 merkkiä!")
    private String description;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    public Job() {
    }

    public Job(Categories category, Employer emp, Types type, List<Area> areas, String jobName, String description) {
        this.category = category;
        this.emp = emp;
        this.type = type;
        this.areas = areas;
        this.jobName = jobName;
        this.description = description;
        this.created = new Date();
    }

    public List<Area> getAreas() {
        if (this.areas == null) {
            this.areas = new ArrayList<>();
        }
        return areas;
    }

    public void setAreas(List<Area> areas) {
        if (this.areas == null) {
            this.areas = new ArrayList<>();
        }
        this.areas = areas;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
