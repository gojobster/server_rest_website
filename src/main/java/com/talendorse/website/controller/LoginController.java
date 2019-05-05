package com.talendorse.website.controller;

import com.talendorse.server.BLL.ConnectionBDManager;
import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.model.Tables;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.server.types.TalendorseErrorType;
import com.talendorse.website.util.UtilOkHttp;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String email_activation(
            HttpServletResponse response,
//            @RequestParam("destination") String url,
            @RequestParam("code") String code,
            @RequestParam("state") String state,
            Model model) {
        try {
            getAccesToken(code);
//            String decodeUrl = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8.name());
//            response.sendRedirect(decodeUrl);
        } catch (TalendorseException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/linkedInAccesToken")
    public String linkedInAccesToken(@RequestParam("access_token") String code, @RequestParam("expires_in") String state, Model model) {

        int a = 0;

        return "email/email_activation_es"; //view
    }

    private void getAccesToken(String authCode) throws TalendorseException {
        try {
            String getUrl = "https://www.linkedin.com/oauth/v2/accessToken";

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


                ConnectionBDManager connection = new ConnectionBDManager();
                UsersRecord usr = connection.create.newRecord(Tables.USERS);

                getUserProfile(accesToken, usr);
                usr.setEmail(getEmail(accesToken));
                usr.store();

            }
        }
        catch (Exception ex) {
            throw new TalendorseException(TalendorseErrorType.LINKEDIM_EXECPTION);
        }
    }

    private void getUserProfile(String accesToken, UsersRecord user) {
        String getUrl = "https://api.linkedin.com/v2/me?projection=(id,email-address,localizedLastName,localizedFirstName,firstName,profilePicture(displayImage~:playableStreams))";

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

                String idLinkedIn = json_data.getString("id");

                String country = json_data.getJSONObject("firstName").getJSONObject("preferredLocale").getString("country");
                String language = json_data.getJSONObject("firstName").getJSONObject("preferredLocale").getString("language");

                String name = json_data.getString("localizedFirstName");
                String surname = json_data.getString("localizedLastName");

                String thumbPictureUrl = json_data.getJSONObject("profilePicture").getJSONObject("displayImage~").getJSONArray("elements")
                        .getJSONObject(0).getJSONArray("identifiers").getJSONObject(0).getString("identifier");

                String profilePictureUrl = json_data.getJSONObject("profilePicture").getJSONObject("displayImage~").getJSONArray("elements")
                        .getJSONObject(2).getJSONArray("identifiers").getJSONObject(0).getString("identifier");

                user.setIdLinkedin(idLinkedIn);
                user.setName(name);
                user.setSurrname(surname);
                user.setPictureUrl(profilePictureUrl);
                user.setThumbUrl(thumbPictureUrl);
                user.setLanguage(language);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private String getEmail(String accesToken) {
        String getUrl = "https://api.linkedin.com/v2/clientAwareMemberHandles?q=members&projection=(elements*(primary,type,handle~))";

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

                return json_data.getJSONArray("elements").getJSONObject(0).getJSONObject("handle~").getString("emailAddress");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "";
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