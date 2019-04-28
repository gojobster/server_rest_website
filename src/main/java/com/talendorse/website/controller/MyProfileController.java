package com.talendorse.website.controller;

import com.talendorse.server.BLL.OffersManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.BLL.UserManagement;
import com.talendorse.server.DTO.RespuestaWSOffer;
import com.talendorse.server.DTO.RespuestaWSUser;
import com.talendorse.server.types.TalendorseErrorType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Controller
public class MyProfileController {
    @GetMapping("/myProfile/{id}")
    public String myProfile(@Context HttpHeaders httpheaders,Model model){

        String token = null;

        try {
            token = httpheaders.getHeaderString("Authorization");
            if (token.isEmpty())
                throw new TalendorseException(TalendorseErrorType.OFFER_NOT_EXISTS);
        } catch (TalendorseException e) {
            e.printStackTrace();
        }

        model.addAttribute("listMyOffers", getUserOffers(token));
        model.addAttribute("listMyEndorsements", getUserEndorsements(token));
        model.addAttribute("myProfile",UserInformation(token));
        return "myProfile"; //view
    }

    private List<RespuestaWSOffer> getUserOffers(String token) {
        List<RespuestaWSOffer> listOffers = new ArrayList<>();
        try {
            listOffers = OffersManagement.getUserOffers(token);
        }
        catch (Exception ex) {}
        return listOffers;
    }

    private List<RespuestaWSOffer> getUserEndorsements(String token) {
        List<RespuestaWSOffer> listOffers = new ArrayList<>();
        try {
            listOffers = OffersManagement.getUserOffers(token);
        }
        catch (Exception ex) {}
        return listOffers;
    }

    private RespuestaWSUser UserInformation(String token) {
        RespuestaWSUser userProfile = null;

        try {
            userProfile = UserManagement.UserInformation(token);
        }
        catch (Exception ex) {
        }
        return userProfile;
    }
}