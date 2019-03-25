package com.jobster.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {
    @GetMapping("/email_activation")
    public String email_activation(Model model) {
        return "email/email_activation_es"; //view
    }

    @GetMapping("/account_activated")
    public String account_activated(Model model) {
        return "email/account_activated"; //view
    }
}