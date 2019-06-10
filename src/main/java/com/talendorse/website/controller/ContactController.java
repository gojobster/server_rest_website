package com.talendorse.website.controller;

import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactController {
    private final static String TAG = ContactController.class.getName();
    @GetMapping("/contact")
    public String main(HttpServletRequest request, Model model){
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);

        return "contact"; //view
    }
}