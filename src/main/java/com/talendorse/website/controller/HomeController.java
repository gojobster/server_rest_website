package com.talendorse.website.controller;

import com.talendorse.server.BLL.*;
import com.talendorse.server.DTO.RespuestaWSOffer;
import com.talendorse.server.DTO.RespuestaWSUser;
import com.talendorse.server.util.Util;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

        model.addAttribute("listOffers", getAllOffers());
        model.addAttribute("url_ws", Constantes.WS_TALENDORSE_URL);

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