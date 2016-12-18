/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.service.JobService;

/**
 *
 * @author mikko
 */
@Controller
@EnableJpaRepositories
public class DefaultController {

    @Autowired
    private JobService jobRepo;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("jobs", jobRepo.all());
        return "index";
    }
}
