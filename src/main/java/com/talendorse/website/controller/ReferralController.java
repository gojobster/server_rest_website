package com.talendorse.website.controller;

import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReferralController {
    private final static String TAG = ReferralController.class.getName();
    @GetMapping("/referral")
    public String main(HttpServletRequest request, Model model){
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);

        return "recomendation"; //view
    }
}