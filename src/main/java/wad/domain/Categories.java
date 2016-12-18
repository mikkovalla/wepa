/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Categories extends AbstractPersistable<Long>{

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany
    private List<Job> jobs;

    public Categories() {
    }

    public Categories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
