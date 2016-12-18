/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.repository.JobRepository;

/**
 *
 * @author mikko
 */
@Controller
public class DefaultController {

    @Autowired
    private JobRepository jobRepo;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("jobs", jobRepo.findAll());
        return "index";
    }

}
