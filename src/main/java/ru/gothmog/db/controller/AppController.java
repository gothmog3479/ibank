package ru.gothmog.db.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author d.grushetskiy
 */
@Controller
public class AppController {

    private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @RequestMapping("/")
    public String hello (Model model){
        model.addAttribute("hello", "Access denied!");
        return "index";
    }
}
