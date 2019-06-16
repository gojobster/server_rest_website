package com.talendorse.website.controller;

import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.OffersManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.BLL.UserManagement;
import com.talendorse.server.DTO.RespuestaWSMyOffer;
import com.talendorse.server.DTO.RespuestaWSMyEndorse;
import com.talendorse.server.DTO.RespuestaWSUser;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyProfileController {
    @GetMapping("/myProfile/{id}")
    public String main(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Model model){
        UtilModel.track_url(response,request);
        String token = UtilModel.addSession(request, model);

        try{
            int userId = CookiesManagement.getIdFromCookie(request);
            if(token == null || (userId != id)){
                response.sendRedirect("/");
                return "index";
            }
        } catch (TalendorseException | IOException e) {
            e.printStackTrace();
        }

        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

        model.addAttribute("listMyOffers", getUserOffers(token));
        model.addAttribute("listMyEndorsements", getUserEndorsements(token));
        model.addAttribute("myProfile",UserInformation(token));
        //return "myProfile"; //view
        return "myProfile"; //view
    }

    private List<RespuestaWSMyOffer> getUserOffers(String token) {
        List<RespuestaWSMyOffer> listOffers = new ArrayList<>();
        try {
            listOffers = OffersManagement.getMyOffers(token);
        }
        catch (Exception ex) {
            return listOffers;
        }
        return listOffers;
    }

    private List<RespuestaWSMyEndorse> getUserEndorsements(String token) {
        List<RespuestaWSMyEndorse> listOffers = new ArrayList<>();
        try {
            listOffers = OffersManagement.getUserMyEndorse(token);
        }
        catch (Exception ex) {
            return listOffers;
        }
        return listOffers;
    }

    private RespuestaWSUser UserInformation(String token) {
        RespuestaWSUser userProfile = null;
        try {
            userProfile = UserManagement.UserInformation(token);
        }
        catch (Exception e){}
        return userProfile;
    }
}