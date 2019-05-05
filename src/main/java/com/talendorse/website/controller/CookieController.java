package com.talendorse.website.controller;

import com.talendorse.server.BLL.Constantes;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Controller
public class CookieController {
    @GetMapping("/createCookie")
    public void createCookie(HttpServletResponse response){
        Cookie loginData = new Cookie("token","112233");
        loginData.setMaxAge(60*60*24*30);
        response.addCookie(loginData);
        try {
            response.sendRedirect("/getCookie");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/getCookie")
    public String home(@CookieValue("token") String token, Model model){ model.addAttribute("token",token);
        return "getCookie";
    }

    /**
     * Method that generates the url for get the authorization token from the Service
     * @return Url
     */
    private static String getAuthorizationUrl(){
        return Constantes.AUTHORIZATION_URL
                +Constantes.QUESTION_MARK+Constantes.RESPONSE_TYPE_PARAM+Constantes.EQUALS+Constantes.RESPONSE_TYPE_VALUE
                +Constantes.AMPERSAND+Constantes.CLIENT_ID_PARAM+Constantes.EQUALS+Constantes.CLIENT_ID
                +Constantes.AMPERSAND+Constantes.STATE_PARAM+Constantes.EQUALS+Constantes.STATE
                +Constantes.AMPERSAND+Constantes.REDIRECT_URI_PARAM+Constantes.EQUALS+Constantes.REDIRECT_URI;
    }


}