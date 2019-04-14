package com.jobster.website.controller;

import com.jobster.server.BLL.Constantes;
import com.jobster.server.DTO.RespuestaWSOffer;
import com.jobster.website.util.UtilOkHttp;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String email_activation(@RequestParam("code") String code, @RequestParam("state") String state, Model model) {
        getAccesToken(code);
        return "email/email_activation_es";
    }

    @GetMapping("/linkedInAccesToken")
    public String linkedInAccesToken(@RequestParam("access_token") String code, @RequestParam("expires_in") String state, Model model) {

        int a = 0;

        return "email/email_activation_es"; //view
    }

    private void getAccesToken(String authCode) {
        try {
            String getUrl = "https://www.linkedin.com/uas/oauth2/accessToken";

            OkHttpClient client = UtilOkHttp.getClientHTTP();
      RequestBody formBody =
          new FormBody.Builder()
              .add("grant_type", "authorization_code")
              .add("code", authCode)
              .add("redirect_uri", "http://localhost:8080/login")
              .add("client_id", Constantes.CLIENT_ID)
              .add("client_secret", Constantes.CLIENT_SECRET)
              .build();

            Request request = new Request.Builder()
                    .url(getUrl)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() == 200 && response.body() != null) {
                String respuesta = response.body().string();

                JSONObject json_data = new JSONObject(respuesta);
                String accesToken = json_data.getString("access_token");


                getProfile(accesToken);
            }
        }
        catch (Exception ex) {
            String a = ex.getMessage();
        }

    }

    private void getProfile(String accesToken) {
        String getUrl = "https://api.linkedin.com/v2/me?projection=(id,firstName,lastName)";

        try {
            OkHttpClient client = UtilOkHttp.getClientHTTP();
            Request request = new Request.Builder()
                    .url(getUrl)
                    .addHeader("Authorization", "Bearer "+accesToken)
                    .addHeader("Connection", "Keep-Alive")
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() == 200 && response.body() != null) {
                String respuesta = response.body().string();
                JSONObject json_data = new JSONObject(respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @GetMapping("/login")
//    public String email_activation(@RequestParam("error") String error, @RequestParam("error_description") String error_description,
//                                   @RequestParam("state") String state, Model model) {
//
//        int a = 0;
//
//        return "email/email_activation_es"; //view
//    }
}