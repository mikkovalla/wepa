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
    public String employerRegister(HttpSession session, @Valid @ModelAttribute("employer") Employer employer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            Employer emp = employer;
            employerService.addEmployer(emp);
            session.setAttribute("employer", emp);
            return "employer";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String loginEmployer(HttpSession session, RedirectAttributes redirect, @RequestParam String username, @RequestParam String password) {
        if (employerService.findByUsername(username) == null) {
            redirect.addAttribute("message", "Väärä käyttäjätunnus");
            return "redirect:/login";
        }
        Employer employer = employerService.findByUsername(username);
        session.setAttribute("employer", employer);
        return "redirect:/employer/";
    }

    @RequestMapping(value = "/employer/logout", method = RequestMethod.POST)
    public String getLogout(HttpSession session) {
        SecurityContextHolder.clearContext();
        session.invalidate();
        return "index";
    }

    @RequestMapping(value = "/employer/{id}/update", method = RequestMethod.POST)
    public String employerUpdate(@Valid @ModelAttribute("employer") Employer employer,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @PathVariable Long id) {

        if (bindingResult.hasErrors()) {
            return "redirect:/employer";
        }
        try {
            Employer emp = employerService.findOne(id);
            emp.setCompanyName(employer.getCompanyName());
            emp.setEmail(employer.getEmail());
            emp.setUsername(employer.getUsername());
            emp.setPassword(employer.getPassword());
            emp.setCompanyDescription(employer.getCompanyDescription());
            employerService.update(emp);

            redirectAttributes.addFlashAttribute("message", "Tiedot päivitetty onnistuneesti!");
            return "redirect:/employer/{id}";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Jotain meni pieleen " + e);
            return "redirect:/employer";
        }
    }

    @RequestMapping(value = "/employer/{id}", method = RequestMethod.GET)
    public String employerDetailsPage(Model model, HttpSession session, @PathVariable Long id) {
        Employer employer = employerService.findOne(id);
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
