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
import org.springframework.web.bind.annotation.RequestMethod;
import wad.service.JobService;

/**
 *
 * @author mikko
 */
@Controller
public class JobsController {

    @Autowired
    private JobService jobsService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String allJobs(Model model) {
        model.addAttribute("jobs", jobsService.all());
        return "index";
    }
}
