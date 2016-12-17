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
@Table(name = "Employee")
public class Employee extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Length(min = 1, max = 50)
    @NotNull
    private String firstName;

    @NotBlank
    @Length(min = 1, max = 100)
    @NotNull
    private String lastname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 5)
    @NotNull
    @Column(unique = true)
    private String username;

    @NotBlank
    @Length(min = 5)
    @NotNull
    private String password;

    @NotBlank
    @Length(min = 10)
    @NotNull
    private String description;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany
    private List<EmployeeJobsApply> employeeJobsApply;

    public Employee() {
    }

    public Employee(String firstName, String lastname, String email, String username, String password, String description, Date created) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.description = description;
        this.created = created;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public List<EmployeeJobsApply> getEmployeeJobsApply() {
        if (this.employeeJobsApply == null) {
            this.employeeJobsApply = new ArrayList<>();
        }
        return employeeJobsApply;
    }

    public void setEmployeeJobsApply(List<EmployeeJobsApply> employeeJobsApply) {
        this.employeeJobsApply = employeeJobsApply;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.username);
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
}
