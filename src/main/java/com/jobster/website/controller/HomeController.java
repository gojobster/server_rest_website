package com.jobster.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/jobster")
    public String home(Model model) {

        return "index"; //view
    }
}