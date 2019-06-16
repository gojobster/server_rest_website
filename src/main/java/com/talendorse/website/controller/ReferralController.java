package com.talendorse.website.controller;

import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ReferralController {
    // final static String TAG = ReferralController.class.getName();
    @GetMapping("/referral/{id}")
    public String main(@PathVariable int id, HttpServletRequest request, Model model, HttpServletResponse response){
        UtilModel.track_url(response,request);
        model.addAttribute("idOffer",id);
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);

        return "recommendation"; //view
    }
}