/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mikko
 */
@Controller
@RequestMapping("*")
public class DefaultController {

    public String index() {
        return "redirect:/index";
    }
}
