package com.talendorse.website.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.OffersManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.DTO.RespuestaWSOffer;
import com.talendorse.server.DTO.RespuestaWSOfferFilters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @GetMapping("/search")
    public String search(@RequestParam("s") String filter_keyword, Model model, HttpServletRequest request) {
        boolean logged = false;
        Integer userId = null;
        try {
            logged = CookiesManagement.cookieHasToken(request);
            userId = CookiesManagement.getIdFromCookie(request);

        } catch (TalendorseException e) {
            e.printStackTrace();
        }
        model.addAttribute("userId",userId);
        model.addAttribute("logged",logged);
        model.addAttribute("listOffers", getAllOffers(filter_keyword));
        model.addAttribute("listFilters", getAllFilters(filter_keyword));
        model.addAttribute("keyword",filter_keyword);
        return "search"; //view
    }

    private List<RespuestaWSOffer> getAllOffers(String filter_keyword) {
        List<RespuestaWSOffer> listOffers = new ArrayList<>();
        try {
            listOffers = OffersManagement.getAllWsOffers(filter_keyword);
        }
        catch (Exception ex) {
            return listOffers;
        }
        return listOffers;
    }

    private RespuestaWSOfferFilters getAllFilters(String filter_keyword) {
        RespuestaWSOfferFilters listFilters = new RespuestaWSOfferFilters();
        try {
            listFilters = OffersManagement.getAllOffersFilters(filter_keyword);
        }
        catch (Exception ex) {
            return listFilters;
        }
        return listFilters;
    }
    private List<RespuestaWSOffer> getFilteredOffers(String keyword, int salary, int experience, List<String> positions, List<String> cities){
        List<RespuestaWSOffer> listOffers = new ArrayList<>();
        try {
            listOffers = OffersManagement.getAllFilteredOffers(keyword, salary, experience, positions, cities);
        }
        catch (Exception ex) {
            return listOffers;
        }
        return listOffers;
    }
}