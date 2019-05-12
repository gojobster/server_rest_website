package com.talendorse.website.controller;

import com.talendorse.server.BLL.*;
import com.talendorse.server.POCO.Offer;
import com.talendorse.server.model.tables.records.UsersRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OfferDetailsController {
    private String codeRef;

    @GetMapping("/offer/{id}")
    public String offerDetails(@PathVariable int id, Model model, HttpServletRequest request) {
        loadOfferDetails(id, model, request);
        return "offerDetails"; //view
    }

    @GetMapping("/offer/{id}/{code}")
    public String offerRecomended(@PathVariable int id, @PathVariable String code, Model model, HttpServletRequest request) {
        this.codeRef = code;
        loadOfferDetails(id, model, request);
        return "offerDetails"; //view
    }

    private void loadOfferDetails (int id, Model model, HttpServletRequest request) {
        boolean logged = false;
        UsersRecord endorser = null;
        Offer offer = null;
        List<Offer> listOffers = null;
        Integer userId = null;
        try {
            userId = CookiesManagement.getIdFromCookie(request);
            if(userId != -1)
                endorser = UserManagement.getUser(userId);

            offer = OffersManagement.getOffer(id);
            logged = CookiesManagement.cookieHasToken(request);
            listOffers =OffersManagement.getAllOffers("","");

        } catch (TalendorseException e) {
            e.printStackTrace();
        }
        model.addAttribute("codeRef", codeRef);
        model.addAttribute("ws_local_url", Constantes.WS_TALENDORSE_URL);
        model.addAttribute("endorser", endorser);
        model.addAttribute("userId",userId);
        model.addAttribute("logged",logged);
        model.addAttribute("offer", offer);
        model.addAttribute("listOffers",listOffers);
    }
}