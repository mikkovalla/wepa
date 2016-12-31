/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Area;
import wad.domain.Categories;
import wad.domain.Employer;
import wad.domain.Job;
import wad.domain.Types;
import wad.repository.AreaRepository;
import wad.repository.CategoriesRepository;
import wad.repository.EmployerRepository;
import wad.repository.JobRepository;
import wad.repository.TypesRepository;

/**
 *
 * @author mikko
 */
@Service
public class JobService {

    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private CategoriesRepository categoryRepo;
    @Autowired
    private TypesRepository typeRepo;
    @Autowired
    EmployerRepository employerRepo;
    @Autowired
    private AreaRepository areaRepo;

    @PostConstruct
    public void init() {
        // demo content
        Categories c1 = categoryRepo.save(new Categories("Front-end devaaja"));
        Categories c2 = categoryRepo.save(new Categories("Back-end devaaja"));
        Categories c3 = categoryRepo.save(new Categories("UI designeri"));
        Categories c4 = categoryRepo.save(new Categories("Fullstack guru"));
        Categories c5 = categoryRepo.save(new Categories("Pelin rakentaja"));
        Categories c6 = categoryRepo.save(new Categories("iOS / Android / Mobile osaaja"));
        Categories c7 = categoryRepo.save(new Categories("Wordpress / Magento / Joomla / Ecommerce häkkääjä"));

        Types t1 = typeRepo.save(new Types("Täys päivä", "#4c9ef1"));
        Types t2 = typeRepo.save(new Types("Osa-aika", "#81b800"));
        Types t3 = typeRepo.save(new Types("Freelancer", "#f4f4f4"));

        Area a1 = areaRepo.save(new Area("Espoo"));
        Area a2 = areaRepo.save(new Area("Helsinki"));
        Area a3 = areaRepo.save(new Area("Tampere"));
        Area a4 = areaRepo.save(new Area("Vantaa"));
        Area a5 = areaRepo.save(new Area("Oulu"));
        Area a6 = areaRepo.save(new Area("Turku"));

        List<Area> areas1 = new ArrayList<>();
        areas1.add(a1);
        areas1.add(a2);
        areas1.add(a3);

        Employer e1 = employerRepo.save(new Employer("JavaGurut", "java@java.com", "javaguru", "javaguru", "javagurut on hieno lafka", new Date()));
        Employer e2 = employerRepo.save(new Employer("Osaajat", "osaajat@osaajat.com", "osaajat", "osaajat", "osaajat on hieno lafka", new Date()));
        Employer e3 = employerRepo.save(new Employer("KoodinVääntäjät", "css@css.com", "csshtml", "csshtml", "Koodinvääntäjät on hieno lafka joka osaa", new Date()));

        Job job1 = new Job(c1, e1, t1, areas1,
                "Html ja Css taituri",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Suspendisse dapibus risus at dapibus malesuada. "
                + "Vestibulum euismod, velit sit amet tempor ornare, "
                + "nulla augue mattis mi, nec ultricies dolor augue nec lectus.");

        Job job2 = new Job(c2, e2, t2, areas1,
                "JavaMaster",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Suspendisse dapibus risus at dapibus malesuada. "
                + "Vestibulum euismod, velit sit amet tempor ornare, "
                + "nulla augue mattis mi, nec ultricies dolor augue nec lectus.");
        Job job3 = new Job(c3, e3, t3, areas1,
                "JavaScript/React/Angular jamppa",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Suspendisse dapibus risus at dapibus malesuada. "
                + "Vestibulum euismod, velit sit amet tempor ornare, "
                + "nulla augue mattis mi, nec ultricies dolor augue nec lectus.");
        List<Job> jobs1 = new ArrayList<>();
        a1.setJobs(jobs1);
        a2.setJobs(jobs1);
        a3.setJobs(jobs1);

        jobRepo.save(job1);
        areaRepo.save(a1);
        areaRepo.save(a2);
        jobRepo.save(job2);
        jobRepo.save(job3);

    }

    public List<Job> all() {
        return jobRepo.findAll();
    }

    public Job findOne(long id) {
        return jobRepo.findOne(id);
    }

    public void delete(long id) {
        jobRepo.delete(jobRepo.findOne(id));
    }

    public List<Job> allByEmployer(String username) {
        return jobRepo.findByEmp_Username(username);
    }

    public void save(Job job) {
        jobRepo.save(job);
    }
}
