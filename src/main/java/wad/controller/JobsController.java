/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.repository.AreaRepository;
import wad.repository.CategoriesRepository;
import wad.service.JobService;

/**
 *
 * @author Mikko
 */
@Controller
public class JobsController {
    
    @Autowired
    private JobService jobService;
    
    @Autowired
    private AreaRepository areaRepo;
    
    @Autowired
    private CategoriesRepository catRepo;
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("jobs", jobService.all());
        model.addAttribute("areas", areaRepo.findAll());
        model.addAttribute("categories", catRepo.findAll());
        return "index";
    }
    
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public String jobDetail(Model model, @PathVariable Long id) {
        model.addAttribute("job", jobService.findOne(id));
        return "details";
    }
    
    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public String jobs(Model model) {
        model.addAttribute("jobs", jobService.all());
        return "jobs";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
