package com.talendorse.website.controller;

import com.talendorse.server.BLL.*;
import com.talendorse.server.POCO.Company;
import com.talendorse.website.util.UtilController;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CompanyController {
    @GetMapping("/company/{id}")
//    public String home(@RequestParam("id") int id, Model model) {
    public String home(@PathVariable int id, HttpServletRequest request, Model model, HttpServletResponse response) {
        UtilModel.track_url(response,request);
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

        Company company = CompaniesManagement.getCompany(id);
        model.addAttribute("company",company);
        model.addAttribute("url_ws", Constantes.WS_TALENDORSE_URL);

        return "companyDetails"; //view
    }

    @GetMapping("/editComany/{id}")
    public String editComany(@PathVariable int id, HttpServletRequest request, Model model, HttpServletResponse response) {
        UtilModel.track_url(response,request);
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

        Company company = null;
        try {
            if(!UtilController.isAdmin(request, token)) {
                response.sendRedirect("/");
                return "index";
            }
            company = CompaniesManagement.getCompany(id);
            model.addAttribute("company",company);

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        model.addAttribute("url_ws", Constantes.WS_TALENDORSE_URL);
        return "companyEdit"; //view
    }
}