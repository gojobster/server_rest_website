package com.talendorse.website.util;

import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.BLL.UserManagement;
import com.talendorse.server.DTO.RespuestaWSUser;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.server.types.RoleType;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilModel {

    public static void addHeaderModel(HttpServletRequest request, Model model, String token){
        String pictureUrl= "";

        try {
            if (token != null)
                pictureUrl = UserManagement.UserInformation(token).urlAvatar;
        } catch (TalendorseException ignored) { }

        model.addAttribute("urlAvatar",pictureUrl);
    }


    public static void addInfoUserModel(HttpServletRequest request, Model model, String token){
        int userId = -1;
        boolean isAdmin = false;
        String name = "";
        String surname = "";
        String email = "";
        String pictureUrl = "";
        UsersRecord user = null;

        if (token != null) {
            try {
                user = UserManagement.GetUserfromToken(token);
            } catch (TalendorseException ignored) {
            }

            if (user != null) {
                userId = user.getIdUser();
                name = user.getName();
                surname = user.getSurname();
                email = user.getEmail();
                pictureUrl = user.getPictureUrl();
                isAdmin = user.getRole() == RoleType.ADMIN.toInt();
            }
        }

        if (!model.containsAttribute("urlAvatar")) {
            model.addAttribute("urlAvatar",pictureUrl);
        }

        if (!model.containsAttribute("userId")) {
            model.addAttribute("userId",userId);
        }

        model.addAttribute("name",name);
        model.addAttribute("surname",surname);
        model.addAttribute("email",email);
        model.addAttribute("isAdmin",isAdmin);
    }


    public static String addSession(HttpServletRequest request, Model model){
        boolean isLogged = false;
        String token = null;
        Integer userId = null;

        try {
            isLogged = CookiesManagement.cookieHasToken(request);
        } catch (TalendorseException ignored) { }

        try {
            token = CookiesManagement.getTokenFromCookie(request);
        } catch (TalendorseException ignored) { }

        try {
            userId = CookiesManagement.getIdFromCookie(request);
        } catch (TalendorseException ignored) { }

        model.addAttribute("logged",isLogged);
        model.addAttribute("token",token);
        model.addAttribute("userId",userId);
        model.addAttribute("ws_local_url", Constantes.WS_TALENDORSE_URL);
        model.addAttribute("local_url", Constantes.TALENDORSE_URL);

        return token;
    }

    public static void track_url(HttpServletResponse response, HttpServletRequest request) {

        String url = request.getRequestURL().toString();
        Cookie cookie = new Cookie("last_url", url);
            if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("last_url")){
                    c.setValue(url);
                    cookie = c;
                }
            }
        }else{
            cookie.setMaxAge(3600);
        }

        response.addCookie(cookie);
    }
}