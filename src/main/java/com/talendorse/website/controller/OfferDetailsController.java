package com.talendorse.website.controller;

import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.BLL.OffersManagement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OfferDetailsController {

    @GetMapping("/offer/{id}")
//    public String home(@RequestParam("id") int id, Model model) {
    public String home(@PathVariable int id, Model model) {
        try {
            model.addAttribute("offer", OffersManagement.getOffer(id));
            model.addAttribute("listOffers", OffersManagement.getAllOffers("",""));
        } catch (TalendorseException e) {
            e.printStackTrace();
        }
        return "offerDetails"; //view
    }
}