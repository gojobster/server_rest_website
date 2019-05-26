package com.talendorse.website.controller;

import com.talendorse.server.BLL.*;
import com.talendorse.server.model.Tables;
import com.talendorse.server.model.tables.records.TokensRecord;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.server.types.TalendorseErrorType;
import com.talendorse.server.util.Email;
import com.talendorse.website.util.UtilModel;
import com.talendorse.website.util.UtilOkHttp;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.talendorse.server.util.Email.TextoMail;

@Controller
public class ContactController {
    private final static String TAG = ContactController.class.getName();
    @GetMapping("/contact")
    public String main(HttpServletRequest request, Model model){
        String token = UtilModel.addSession(request, model);
        UtilModel.addHeaderModel(request, model, token);

        return "contact"; //view
    }
}