package com.talendorse.website.controller;

import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.OffersManagement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {
        request.getCookies();
        boolean logged = false;
        for (Cookie c: request.getCookies()) {
            if (c.getName().equals("token"))
                logged = true;
        }
        model.addAttribute("logged",logged);
        model.addAttribute("url_ws", Constantes.WS_TALENDORSE_URL);
        return "index"; //view
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


    /**
     * Method that generates the url for get the access token from the Service
     * @return Url
     */
    private static String getAccessTokenUrl(String authorizationToken){
        return Constantes.ACCESS_TOKEN_URL
                +Constantes.QUESTION_MARK
                +Constantes.GRANT_TYPE_PARAM+Constantes.EQUALS+Constantes.GRANT_TYPE
                +Constantes.AMPERSAND
                +Constantes.RESPONSE_TYPE_VALUE+Constantes.EQUALS+authorizationToken
                +Constantes.AMPERSAND
                +Constantes.CLIENT_ID_PARAM+Constantes.EQUALS+Constantes.CLIENT_ID
                +Constantes.AMPERSAND
                +Constantes.REDIRECT_URI_PARAM+Constantes.EQUALS+Constantes.REDIRECT_URI
                +Constantes.AMPERSAND
                +Constantes.SECRET_KEY_PARAM+Constantes.EQUALS+Constantes.CLIENT_SECRET;
    }
}