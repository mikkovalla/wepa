/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String employerRegister(@Valid @ModelAttribute("employer") Employer employer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (employerService.findByUsername(employer.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.employer", "Käyttäjä tunnus on jo olemassa!");
        } else if (employerService.findByEmail(employer.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.employer", "Email osoite on jo rekisteröity palveluun!");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            Employer emp = new Employer();
            emp.setCompanyName(employer.getCompanyName());
            emp.setEmail(employer.getEmail());
            emp.setUsername(employer.getUsername());
            emp.setPassword(employer.getPassword());
            emp.setCompanyDescription(employer.getCompanyDescription());
            emp.setCreated(new Date());
            employerService.addEmployer(emp);

            redirectAttributes.addFlashAttribute("message", "Tervetuloa palveluun " + emp.getCompanyName() + " Voit nyt kirjautua sisään!");
            return "employer";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String loginEmployer(RedirectAttributes redirect, @RequestParam String username, @RequestParam String password) {
        if (employerService.findByUsername(username) == null) {
            redirect.addAttribute("message", "Väärä käyttäjätunnus");
            return "redirect:/login";
        }

        return "employer";
    }

    @RequestMapping(value = "/employer/logout", method = RequestMethod.POST)
    public String getLogout() {
        SecurityContextHolder.clearContext();
        return "index";
    }

    @RequestMapping(value = "/employer/{id}/update", method = RequestMethod.POST)
    public String employerUpdate(@Valid @ModelAttribute("employer") Employer employer,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model,
            @PathVariable Long id) {

        if (bindingResult.hasErrors()) {
            return "employer";
        }
        try {
            Employer emp = employerService.findOne(id);
            emp.setCompanyName(employer.getCompanyName());
            emp.setEmail(employer.getEmail());
            emp.setUsername(employer.getUsername());
            emp.setPassword(employer.getPassword());
            emp.setCompanyDescription(employer.getCompanyDescription());
            employerService.update(emp);

            redirectAttributes.addAttribute("message", "Tiedot päivitetty onnistuneesti!");
            return "redirect:/employer";
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "Jotain meni pieleen" + e);
            return "redirect:/employer";
        }
    }

    @RequestMapping(value = "/employer", method = RequestMethod.GET)
    public String employerDetailsPage(Model model, HttpSession session) {
        Employer employer = employerService.loggedIn();
        session.setAttribute("employer", employer);
        model.addAttribute("employerJobs", jobService.allByEmployer(employerService.loggedIn().getUsername()));
        return "employer";
    }

    @RequestMapping(value = "/employer/delete", method = RequestMethod.DELETE)
    public String deleteEmployer(RedirectAttributes redirectAttributes) {
        SecurityContextHolder.clearContext();
        employerService.delete(employerService.getLoggedInId());
        redirectAttributes.addFlashAttribute("message", "Tietosi on poistettu palvelusta");
        return "redirect:/index";
    }
}
