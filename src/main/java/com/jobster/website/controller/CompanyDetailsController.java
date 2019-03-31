package com.jobster.website.controller;

import com.jobster.server.BLL.CompaniesManagement;
import com.jobster.server.POCO.Company;
import com.jobster.server.model.tables.records.CompaniesRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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