/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
    private Categories cat;

    @ManyToOne
    @JoinColumn
    private Employer emp;

    @ManyToOne
    @JoinColumn
    private Types type;

    @ManyToOne
    @JoinColumn
    private Area area;

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

    public Job(Categories cat, Employer emp, Types type, Area area, String jobName, String description) {
        this.cat = cat;
        this.emp = emp;
        this.type = type;
        this.area = area;
        this.jobName = jobName;
        this.description = description;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
