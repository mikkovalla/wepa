/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.util.Date;
import javax.servlet.ServletException;
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

/**
 *
 * @author Mikko
 */
@Controller
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @RequestMapping(value = "/register/employer", method = RequestMethod.POST)
    public String employerRegister(@Valid @ModelAttribute("employer") Employer employer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (employerService.findByUsername(employer.getEmail()) != null) {
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
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @RequestMapping(value = "employer/logout", method = RequestMethod.POST)
    public String getLogout(HttpSession httpSession, Model model) throws ServletException {
        SecurityContextHolder.clearContext();
        return "index";
    }
}
