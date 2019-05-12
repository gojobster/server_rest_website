package com.talendorse.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {
    @GetMapping("/email/activation_es")
    public String email_activation(Model model) {
        return "email/email_activation_es"; //view
    }

    @GetMapping("/email/activation_en")
    public String account_activated(Model model) {
        return "email/account_activated"; //view

    }@GetMapping("/email/recommendation_es")
    public String email_recomandation(Model model) {
        return "email/email_activation_es"; //view
    }

    @GetMapping("/email/recommendation_en")
    public String account_recomandation_en(Model model) {
//        try {
//            UsersRecord endorser = EndorsementManagement.getEndorser(referral_code);
//            UsersRecord candidate = EndorsementManagement.getCandidate(referral_code);
//
//            model.addAttribute("endorser", endorser);
//            model.addAttribute("candidate", candidate);
//        } catch (TalendorseException e) {
//            TODO: redirect to error page
//            e.printStackTrace();
//        }

        return "email/email_recomendation_en"; //view
    }
}