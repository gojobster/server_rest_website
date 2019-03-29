package com.jobster.website.controller;

import com.jobster.server.BLL.CompaniesManagement;
import com.jobster.server.POCO.Company;
import com.jobster.server.model.tables.records.CompaniesRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompanyDetailsController {
    public Company company;

    @GetMapping("/company")
    public String home(@RequestParam("id_company") int id_copmany, Model model) {
        company = CompaniesManagement.getCompany(id_copmany);

        model.addAttribute("company", company);
        return "companyDetails"; //view
    }
}