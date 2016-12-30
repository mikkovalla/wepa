/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Area;
import wad.domain.Categories;
import wad.domain.Job;
import wad.domain.Types;
import wad.repository.AreaRepository;
import wad.repository.CategoriesRepository;
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
    private LoggedInEmployerService employerLoggedIn;
    @Autowired
    private AreaRepository areaRepo;

    @PostConstruct
    public void init() {
        // demo content
        categoryRepo.save(new Categories("Front-end devaaja"));
        categoryRepo.save(new Categories("Back-end devaaja"));
        categoryRepo.save(new Categories("UI designeri"));
        categoryRepo.save(new Categories("Fullstack guru"));
        categoryRepo.save(new Categories("Pelin rakentaja"));
        categoryRepo.save(new Categories("iOS / Android / Mobile osaaja"));
        categoryRepo.save(new Categories("Wordpress / Magento / Joomla / Ecommerce häkkääjä"));

        typeRepo.save(new Types("Täys päivä", "#4c9ef1"));
        typeRepo.save(new Types("Osa-aika", "#81b800"));
        typeRepo.save(new Types("Freelancer", "#f4f4f4"));
        
        areaRepo.save(new Area("Espoo"));
        areaRepo.save(new Area("Helsinki"));
        areaRepo.save(new Area("Tampere"));
        areaRepo.save(new Area("Vantaa"));
        areaRepo.save(new Area("Oulu"));
        areaRepo.save(new Area("Turku"));

        jobRepo.save(new Job(categoryRepo.findOne(1L), employerService.findOne(1L), typeRepo.findOne(1L), areaRepo.findOne(1L),
                "Html ja Css taituri",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Suspendisse dapibus risus at dapibus malesuada. "
                + "Vestibulum euismod, velit sit amet tempor ornare, "
                + "nulla augue mattis mi, nec ultricies dolor augue nec lectus."));
        jobRepo.save(new Job(categoryRepo.findOne(2L), employerService.findOne(2L), typeRepo.findOne(2L), areaRepo.findOne(2L),
                "JavaMaster",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Suspendisse dapibus risus at dapibus malesuada. "
                + "Vestibulum euismod, velit sit amet tempor ornare, "
                + "nulla augue mattis mi, nec ultricies dolor augue nec lectus."));
        jobRepo.save(new Job(categoryRepo.findOne(3L), employerService.findOne(3L), typeRepo.findOne(3L), areaRepo.findOne(3L),
                "JavaScript/React/Angular jamppa",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Suspendisse dapibus risus at dapibus malesuada. "
                + "Vestibulum euismod, velit sit amet tempor ornare, "
                + "nulla augue mattis mi, nec ultricies dolor augue nec lectus."));
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
}
