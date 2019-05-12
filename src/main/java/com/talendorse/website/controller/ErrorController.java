package com.talendorse.website.controller;

import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.TalendorseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String error(Model model, HttpServletRequest request) {
        return "error"; //view
    }
}