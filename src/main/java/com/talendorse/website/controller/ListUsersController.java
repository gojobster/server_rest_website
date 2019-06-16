package com.talendorse.website.controller;

import com.talendorse.server.BLL.*;
import com.talendorse.server.DTO.RespuestaWSOffer;
import com.talendorse.server.DTO.RespuestaWSUser;
import com.talendorse.server.POCO.UserPOCO;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.server.types.RoleType;
import com.talendorse.website.util.UtilModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.talendorse.server.BLL.UserManagement.getAllUsers;

@Controller
public class ListUsersController {
    @GetMapping("/listUsers")
    public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);
        UtilModel.addInfoUserModel(request,model,token);

        try{
            int userId = CookiesManagement.getIdFromCookie(request);
            UsersRecord user = UserManagement.getUser(userId);

            if(token == null || user.getRole() != RoleType.ADMIN.toInt()){
                response.sendRedirect("/");
                return "index";
            }
        } catch (TalendorseException | IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("listUsers", getAllUsers());
        model.addAttribute("url_ws", Constantes.WS_TALENDORSE_URL);

        return "listUsers"; //view
    }

    private List<UserPOCO> getAllUsers() {
        List<UserPOCO> listUsers = new ArrayList<>();
        try {
            listUsers = UserManagement.getAllUsersPOCO();
        }
        catch (Exception ex) {
            return listUsers;
        }
        return listUsers;
    }
}