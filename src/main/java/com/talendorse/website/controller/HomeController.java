package com.talendorse.website.controller;

import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.BLL.UserManagement;
import com.talendorse.server.DTO.RespuestaWSUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {
        RespuestaWSUser profile = null;
        boolean logged = false;
        String token = null;
        Integer userId = null;
        try {
            logged = CookiesManagement.cookieHasToken(request);
            token = CookiesManagement.getTokenFromCookie(request);
            userId = CookiesManagement.getIdFromCookie(request);
            if (token != null)
                profile = UserManagement.UserInformation(token);

        } catch (TalendorseException e) {
            e.printStackTrace();
        }
        model.addAttribute("profile",profile);
        model.addAttribute("token",token);
        model.addAttribute("userId",userId);
        model.addAttribute("logged",logged);
        model.addAttribute("url_ws", Constantes.WS_TALENDORSE_URL);

        return "index"; //view
    }
}