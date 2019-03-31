package com.jobster.website.controller;

import com.jobster.server.BLL.OffersManagement;
import com.jobster.server.DTO.RespuestaWSOffer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    public List<RespuestaWSOffer> listOffers = new ArrayList<>();

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String filter_keyword,
                         @RequestParam("city") String filter_city, Model model) {

        getAllOffers(filter_keyword, filter_city);

        model.addAttribute("listOffers", listOffers);
        return "search"; //view
    }

    private void getAllOffers(String filter_keyword, String filter_city) {
        try {
            listOffers= OffersManagement.getAllOffers(filter_keyword, filter_city);
        }
        catch (Exception ex) {
            String a = ex.getMessage();
        }
    }
}