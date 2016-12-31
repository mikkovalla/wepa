/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Area;
import wad.domain.Categories;
import wad.domain.Employer;
import wad.domain.Job;
import wad.domain.Types;
import wad.repository.AreaRepository;
import wad.repository.CategoriesRepository;
import wad.repository.TypesRepository;
import wad.service.EmployerService;
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

    @Autowired
    private TypesRepository typeRepo;

    @Autowired
    private EmployerService employerService;

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
        model.addAttribute("areas", areaRepo.findAll());
        model.addAttribute("categories", catRepo.findAll());
        return "jobs";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("employer", new Employer());
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/jobs/addjob", method = RequestMethod.GET)
    public String addjobPage(HttpSession session, Model model) {
        session.setAttribute("employer", employerService.loggedIn());
        model.addAttribute("job", new Job());
        model.addAttribute("areas", areaRepo.findAll());
        model.addAttribute("categories", catRepo.findAll());
        model.addAttribute("types", typeRepo.findAll());

        return "addjob";
    }

    @RequestMapping(value = "/addjob", method = RequestMethod.POST)
    public String addjob(@Valid @ModelAttribute("job") Job job,
            BindingResult bindingResult, HttpSession session,
            Model model,
            @RequestParam Long typeId,
            @RequestParam List<Area> areas,
            @RequestParam Long categoryId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "addjob";
        }
        Employer emp = (Employer) session.getAttribute("employer");
        Categories cat = catRepo.findOne(categoryId);
        Types type = typeRepo.findOne(typeId);
        areas = new ArrayList<>();

        Job jobi = new Job(cat, emp, type, areas, job.getJobName(), job.getDescription());
        jobService.save(jobi);
        return "redirect:/employer";
    }

    @RequestMapping(value = "/jobs/{id}/delete", method = RequestMethod.POST)
    public String deleteJob(@PathVariable Long id, RedirectAttributes redirect) {
        jobService.delete(id);
        redirect.addFlashAttribute("message", "Duuni poistettu palvelusta!");
        return "redirect:/employer";
    }
}
