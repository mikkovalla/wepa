/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Employer;
import wad.service.EmployerService;
import wad.service.JobService;

/**
 *
 * @author Mikko
 */
@Controller
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String employerRegister(HttpSession session, @Valid @ModelAttribute("employer") Employer employer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        Employer emp = employer;
        emp.setAuthority("EMPLOYER");
        employerService.addEmployer(emp);
        return "redirect:/login";
    }
    @RequestMapping(value = "/employer/logout", method = RequestMethod.POST)
    public String getLogout(HttpSession session) {
        SecurityContextHolder.clearContext();
        session.invalidate();
        return "/";
    }

    @RequestMapping(value = "/employer/update", method = RequestMethod.POST)
    public String employerUpdate(@Valid @ModelAttribute Employer employer,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:/employer";
        }
        try {
            Employer emp = employerService.loggedIn();
            
            emp.setCompanyName(employer.getCompanyName());
            emp.setEmail(employer.getEmail());
            emp.setUsername(employer.getUsername());
            emp.setPassword(employer.getPassword());
            emp.setCompanyDescription(employer.getCompanyDescription());
            
            employerService.update(emp);

            redirectAttributes.addFlashAttribute("message", "Tiedot päivitetty onnistuneesti!");
            return "redirect:/employer";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Jotain meni pieleen " + e);
            return "redirect:/employer";
        }
    }

    @RequestMapping(value = "/employer", method = RequestMethod.GET)
    public String employerDetailsPage(Model model, HttpSession session) {
        
        Employer employer = employerService.loggedIn();
        session.setAttribute("employer", employer);
        model.addAttribute("employer", employer);
        model.addAttribute("employerJobs", jobService.allByEmployer(employerService.loggedIn().getUsername()));
        
        return "employer";
    }

    @RequestMapping(value = "/employer/delete", method = RequestMethod.POST)
    public String deleteEmployer() {
        employerService.delete(employerService.loggedIn());
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}
