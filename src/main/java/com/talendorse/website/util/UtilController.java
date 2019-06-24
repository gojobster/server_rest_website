package com.talendorse.website.util;

import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.BLL.UserManagement;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.server.types.RoleType;
import com.talendorse.server.types.TalendorseErrorType;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UtilController {

    public static boolean checkAdminPermissisons (Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            UtilModel.track_url(response,request);
            String token = UtilModel.addSession(request, model);
            UtilModel.addHeaderModel(request, model, token);
            UtilModel.addInfoUserModel(request,model,token);

            int userId = CookiesManagement.getIdFromCookie(request);
            UsersRecord user = UserManagement.getUser(userId);

            return token != null && user.getRole() == RoleType.ADMIN.toInt();

        } catch (TalendorseException e) {
            e.printStackTrace();
            return false;
        }
    }
}