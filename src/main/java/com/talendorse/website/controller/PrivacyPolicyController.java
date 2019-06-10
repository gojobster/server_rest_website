package com.talendorse.website.controller;

import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PrivacyPolicyController {
    @GetMapping("/privacy")
    public String error(Model model, HttpServletRequest request) {
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);



        return "privacy_policy";
    }
}