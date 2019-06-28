package com.talendorse.website.controller;

import com.talendorse.server.BLL.*;
import com.talendorse.server.DTO.RespuestaWSUser;
import com.talendorse.server.POCO.Offer;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.server.types.TalendorseErrorType;
import com.talendorse.website.util.UtilController;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class OfferDetailsController {
    private String codeRef;

    @GetMapping("/offer/{id}")
    public String offerDetails(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        loadOfferDetails(id, model, request, response);
        return "offerDetails"; //view
    }

    @GetMapping("/offer/{id}/{code}")
    public String offerRecomended(@PathVariable int id, @PathVariable String code, Model model, HttpServletRequest request, HttpServletResponse response) {
        this.codeRef = code;
        loadOfferDetails(id, model, request, response);
        return "offerDetails"; //view
    }

    @GetMapping("/editOffer/{id}")
    public String offerEdit(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        UtilModel.track_url(response,request);
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

        Offer offer = null;
        try {
            if(!UtilController.isAdmin(request, token)) {
                response.sendRedirect("/");
                return "index";
            }
            offer = OffersManagement.getOffer(id);

            if(offer == null) throw new TalendorseException(TalendorseErrorType.OFFER_ERROR);

        } catch (TalendorseException | IOException e) {
            e.printStackTrace();
            return "error";
        }

        model.addAttribute("offer", offer);
        return "offerEdit"; //view
    }

    private void loadOfferDetails (int idOffer, Model model, HttpServletRequest request, HttpServletResponse response) {
        UtilModel.track_url(response,request);
        boolean userApplied = false;
        UsersRecord endorser = null;
        Offer offer = null;
        List<Offer> listOffers = null;
        try {
            int userId = CookiesManagement.getIdFromCookie(request);
            if(userId != -1)
                endorser = UserManagement.getUser(userId);

            offer = OffersManagement.getOffer(idOffer);

            userApplied = OffersManagement.userApplied(idOffer, userId);
            listOffers =OffersManagement.getRelatedOffers(idOffer);

        } catch (TalendorseException e) {
            e.printStackTrace();
        }

        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

        model.addAttribute("userApplied", userApplied);
        model.addAttribute("codeRef", codeRef);
        model.addAttribute("endorser", endorser);
        model.addAttribute("offer", offer);
        model.addAttribute("listOffers",listOffers);
    }
}