/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author mikko
 */
@Entity
@Table(name = "Employer")
public class Employer extends AbstractPersistable<Long>{

    @NotBlank
    @Length(min = 1)
    @NotNull
    @Column(unique = true)
    private String companyName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 5)
    @NotNull
    @Column(unique = true)
    private String username;

    @NotBlank
    @NotNull
    @Length(min = 5)
    @Column(unique = true)
    private String password;

    @NotNull
    private String companyDescription;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany
    private List<Job> jobs;

    public Employer() {
    }

    public Employer(String companyName, String email, String username, String password, String companyDescription, Date created) {
        this.companyName = companyName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.companyDescription = companyDescription;
        this.created = created;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Job> getJobs() {
        if (this.jobs == null) {
            this.jobs = new ArrayList<>();
        }
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.companyName);
        hash = 59 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employer other = (Employer) obj;
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

}
