package com.talendorse.website.controller;

import com.talendorse.server.BLL.*;
import com.talendorse.server.POCO.Offer;
import com.talendorse.server.POCO.UserPOCO;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.server.types.RoleType;
import com.talendorse.website.util.UtilController;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ListOffersController {
    @GetMapping("/listOffers")
    public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
        if(!UtilController.checkAdminPermissisons(model, request, response)) {
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "index";
        }

        model.addAttribute("listOffers", getAllOffers());
        model.addAttribute("url_ws", Constantes.WS_TALENDORSE_URL);

        return "listOffers"; //view
    }

    private List<Offer> getAllOffers() {
        List<Offer> listOffers = new ArrayList<>();
        try {
            listOffers = OffersManagement.getAllOffers("","");
        }
        catch (Exception ex) {
            return listOffers;
        }
        return listOffers;
    }
}