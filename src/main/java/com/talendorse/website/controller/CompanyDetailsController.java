package com.talendorse.website.controller;

import com.talendorse.server.BLL.CompaniesManagement;
import com.talendorse.server.POCO.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompanyDetailsController {
    public Company company;

    @GetMapping("/company/{id}")
//    public String home(@RequestParam("id") int id, Model model) {
    public String home(@PathVariable int id, Model model) {
        company = CompaniesManagement.getCompany(id);

        model.addAttribute("company", company);
        return "companyDetails"; //view
    }
}