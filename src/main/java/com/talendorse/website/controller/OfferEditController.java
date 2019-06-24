package com.talendorse.website.controller;

import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.OffersManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.BLL.UserManagement;
import com.talendorse.server.POCO.Offer;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class OfferEditController {
    @GetMapping("/editOffer/{id}")
    public String offerDetails(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        loadOfferDetails(id, model, request, response);
        return "offerEdit"; //view
    }

    private void loadOfferDetails (int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        UtilModel.track_url(response,request);
        boolean userApplied = false;
        UsersRecord endorser = null;
        Offer offer = null;
        List<Offer> listOffers = null;
        try {
            int userId = CookiesManagement.getIdFromCookie(request);
            if(userId != -1)
                endorser = UserManagement.getUser(userId);

            offer = OffersManagement.getOffer(id);

            userApplied = OffersManagement.userApplied(id, userId);
            listOffers =OffersManagement.getAllOffers("","");

        } catch (TalendorseException e) {
            e.printStackTrace();
        }

        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

        model.addAttribute("userApplied", userApplied);
        model.addAttribute("endorser", endorser);
        model.addAttribute("offer", offer);
        model.addAttribute("listOffers",listOffers);
    }
}