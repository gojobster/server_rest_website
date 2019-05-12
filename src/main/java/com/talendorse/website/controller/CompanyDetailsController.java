package com.talendorse.website.controller;

import com.talendorse.server.BLL.CompaniesManagement;
import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.POCO.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CompanyDetailsController {
    @GetMapping("/company/{id}")
//    public String home(@RequestParam("id") int id, Model model) {
    public String home(@PathVariable int id, HttpServletRequest request, Model model) {
        Company company = null;
        boolean logged = false;

        try {
            company = CompaniesManagement.getCompany(id);
            logged = CookiesManagement.cookieHasToken(request);

        } catch (TalendorseException e) {
            e.printStackTrace();
        }
        model.addAttribute("token",company);
        model.addAttribute("logged",logged);
        model.addAttribute("url_ws", Constantes.WS_TALENDORSE_URL);

        return "companyDetails"; //view
    }
}