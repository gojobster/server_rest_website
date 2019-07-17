package com.talendorse.website.controller;

import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.OffersManagement;
import com.talendorse.server.DTO.RespuestaWSOffer;
import com.talendorse.server.DTO.RespuestaWSOfferFilters;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController{

    @GetMapping("/search")
    public String main(Model model,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam("s") String filter_keyword,
                       @RequestParam(value = "city", required=false, defaultValue="") String city,
                       @RequestParam(value = "salary", required=false, defaultValue="0")  int salary,
                       @RequestParam(value = "experience", required=false, defaultValue="99") int experience) {

        UtilModel.track_url(response,request);
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

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
            listOffers = OffersManagement.getAllFilteredOffers(keyword, salary, experience, positions, cities, Constantes.PAGE_SIZE_OFFERS , 0, "DESC");
        }
        catch (Exception ex) {
            return listOffers;
        }
        return listOffers;
    }
}