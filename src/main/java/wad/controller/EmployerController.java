/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.service.EmployerService;

/**
 *
 * @author mikko
 */
@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;
}
