package com.talendorse.website.controller;

import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.OffersManagement;
import com.talendorse.server.DTO.RespuestaWSOffer;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LinkedInController {
    @GetMapping("/login_linkin")
    public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
        UtilModel.track_url(response,request);
        return "index"; //view
    }

    private List<RespuestaWSOffer> getAllOffers() {
        List<RespuestaWSOffer> listOffers = new ArrayList<>();
        try {
            listOffers = OffersManagement.getAllWsOffers("");
        }
        catch (Exception ex) {
            return listOffers;
        }
        return listOffers;
    }
}