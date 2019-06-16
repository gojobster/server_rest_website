package com.talendorse.website.controller;

import com.talendorse.server.BLL.ConnectionBDManager;
import com.talendorse.server.BLL.UserManagement;
import com.talendorse.server.BLL.TokenManagement;
import com.talendorse.server.BLL.CookiesManagement;
import com.talendorse.server.BLL.LogManagement;
import com.talendorse.server.BLL.TalendorseException;
import com.talendorse.server.BLL.Constantes;
import com.talendorse.server.model.Tables;
import com.talendorse.server.model.tables.records.TokensRecord;
import com.talendorse.server.model.tables.records.UsersRecord;
import com.talendorse.server.types.TalendorseErrorType;
import com.talendorse.server.util.Email;
import com.talendorse.website.util.UtilOkHttp;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.talendorse.server.util.Email.TextoMail;

@Controller
public class LoginController {
    private final static String TAG = LoginController.class.getName();
    @GetMapping("/logout")
    public String main(HttpServletResponse response){
        try {
            CookiesManagement.deleteTokenCookie(response);
        } catch (TalendorseException | IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/login_wp")
    public String login_wp(
            HttpServletResponse response,
            @RequestParam("code") String code,
            @RequestParam("state") String state) {

        try {
            LogManagement.addLog(TAG, "Login: LinkedIn" + code);
            String accessToken = getAccessToken(code);
            LogManagement.addLog(TAG, "getaccessToken!" + accessToken);

            if (accessToken != null) {
                boolean new_user = false;

                ConnectionBDManager connection = new ConnectionBDManager();
                LogManagement.addLog(TAG, "Conexion creada!");

                UsersRecord linkedinUser = createLinkedInUserProfile(accessToken);
                LogManagement.addLog(TAG, "Creado Usuarios con perfil linkedin id:"+ linkedinUser);

                UsersRecord usr = UserManagement.getUserbyIdLinkedIn(connection, linkedinUser.getIdLinkedin());
                LogManagement.addLog(TAG, "Usuario" + usr);

                if(usr == null) {
                    new_user = true;
                    usr = connection.create.newRecord(Tables.USERS);
                }

                UserManagement.copyUserFromLinkedInUSer(linkedinUser, usr);
                usr.store();
                connection.closeConnection();

                if(new_user)
                    sendEmail(usr.getName(), usr.getEmail());

                LogManagement.addLog(TAG, "Usuario Añadido a la BD");

                TokenManagement.addToken(usr.getIdUser());
                LogManagement.addLog(TAG, "Token añadido!");
            }

            LogManagement.addLog(TAG, "Redirigiendo...");
            response.sendRedirect("http://www.talendorse.com/bienvenido");
        } catch (TalendorseException | IOException e) {
            LogManagement.addLog(TAG, "Error:" + e.getMessage());
            e.printStackTrace();
        }
        return "error"; //TODO: crear un html generico para errores;
    }

    @GetMapping("/login")
    public String main(
            HttpServletResponse response,
            @RequestParam(value = "code", required=false) String code,
            @RequestParam("state") String state,
            @RequestParam(value = "error", required=false) String error,
            @RequestParam(value = "error_description", required=false) String error_description) {

        if (error != null && error.equalsIgnoreCase("user_cancelled_login")) {
            LogManagement.addLog(TAG, "Usuario ha cancelado el login con linkedin");
            try {
                response.sendRedirect("/"); ///TODO: redirigir donde estaba antes el usuario
                return "error";
            } catch (IOException e) {
                LogManagement.addLog(TAG, e.getMessage());
                return "error";
            }
        }

        try {
            LogManagement.addLog(TAG, "Login: LinkedIn" + code);
            Cookie loginData = createCookie(code);
            LogManagement.addLog(TAG, "Cookie creada!" + loginData);

            if (loginData != null) {
                boolean new_user = false;

                ConnectionBDManager connection = new ConnectionBDManager();
                LogManagement.addLog(TAG, "Conexion creada!");

                UsersRecord linkedinUser = createLinkedInUserProfile(loginData.getValue());
                LogManagement.addLog(TAG, "Creado Usuarios con perfil linkedin id:"+ linkedinUser);

                UsersRecord usr = UserManagement.getUserbyIdLinkedIn(connection, linkedinUser.getIdLinkedin());
                LogManagement.addLog(TAG, "Usuario" + usr);

                if(usr == null) {
                    new_user = true;
                    usr = connection.create.newRecord(Tables.USERS);
                }

                UserManagement.copyUserFromLinkedInUSer(linkedinUser, usr);
                usr.store();
                connection.closeConnection();

                if(new_user)
                    sendEmail(usr.getName(), usr.getEmail());

                LogManagement.addLog(TAG, "Usuario Añadido a la BD");

                TokensRecord token = TokenManagement.addToken(usr.getIdUser());
                LogManagement.addLog(TAG, "Token añadido!");
                loginData.setValue(token.getToken());

                response.addCookie(loginData);
                LogManagement.addLog(TAG, "Cookie Añadida...");
            }

            LogManagement.addLog(TAG, "Redirigiendo...");
            response.sendRedirect("/");
        } catch (TalendorseException | IOException e) {
            LogManagement.addLog(TAG, "Error:" + e.getMessage());
            e.printStackTrace();
        }
        return "error"; //TODO: crear un html generico para errores;
    }

    private void sendEmail(String username, String user_email) {
        try {
            String url = Constantes.TALENDORSE_URL;
            String url_location = Constantes.URL_EMAIL_NEW_USER_URL_EN;
            String email_subject = Constantes.EMAIL_SUBJECT_NEW_USER_ES;

            String textoEmail = TextoMail(url, url_location);
            textoEmail = textoEmail.replace("{Username}", username);

            Email.sendEmail(user_email, email_subject, textoEmail);
        } catch (TalendorseException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/linkedInAccesToken")
    public String linkedInAccesToken(@RequestParam("access_token") String code, @RequestParam("expires_in") String state, Model model) {

        int a = 0;

        return "email/email_activation_es"; //view
    }

    private Cookie createCookie (String authCode) throws TalendorseException {
        Cookie loginData = null;
        try {
            String getUrl = "https://www.linkedin.com/oauth/v2/accessToken";
            OkHttpClient client = UtilOkHttp.getClientHTTP();
      RequestBody formBody =
          new FormBody.Builder()
              .add("grant_type", "authorization_code")
              .add("code", authCode)
              .add("redirect_uri",  Constantes.REDIRECT_URL_LINKEDIN)
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

                loginData = new Cookie("token",json_data.getString("access_token"));
                loginData.setMaxAge(json_data.getInt("expires_in"));
            }
        }
        catch (Exception ex) {
            throw new TalendorseException(TalendorseErrorType.LINKEDIM_EXECPTION);
        }
        return loginData;
    }


    private String getAccessToken (String authCode) throws TalendorseException {
        String accessToken = null;
        try {
            String getUrl = "https://www.linkedin.com/oauth/v2/accessToken";

            OkHttpClient client = UtilOkHttp.getClientHTTP();
            RequestBody formBody =
                    new FormBody.Builder()
                            .add("grant_type", "authorization_code")
                            .add("code", authCode)
                            .add("redirect_uri",  Constantes.REDIRECT_URL_LINKEDIN_WP)
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

                accessToken = json_data.getString("access_token");
            }
        }
        catch (Exception ex) {
            throw new TalendorseException(TalendorseErrorType.LINKEDIM_EXECPTION);
        }
        return accessToken;
    }

    private UsersRecord createLinkedInUserProfile(String accesToken) {
        String getUrl = "https://api.linkedin.com/v2/me?projection=(id,email-address,localizedLastName,localizedFirstName,firstName,profilePicture(displayImage~:playableStreams))";

        UsersRecord user = null;

        try {
            OkHttpClient client = UtilOkHttp.getClientHTTP();
            Request request = new Request.Builder()
                    .url(getUrl)
                    .addHeader("Authorization", "Bearer "+accesToken)
                    .addHeader("Connection", "Keep-Alive")
                    .build();

            Response response = client.newCall(request).execute();
            LogManagement.addLog(TAG, ""+response.code());
            if (response.code() == 200 && response.body() != null) {
                String respuesta = response.body().string();
                JSONObject json_data = new JSONObject(respuesta);
                LogManagement.addLog(TAG,respuesta);

                String idLinkedIn = json_data.getString("id");

                String country = json_data.getJSONObject("firstName").getJSONObject("preferredLocale").getString("country");
                String language = json_data.getJSONObject("firstName").getJSONObject("preferredLocale").getString("language");

                String name = json_data.getString("localizedFirstName");
                String surname = json_data.getString("localizedLastName");

                //TODO: crear una imagen por defecto para los usuarios que no tienen una imagen.
                String thumbPictureUrl = null;
                try {
                    thumbPictureUrl = json_data.getJSONObject("profilePicture").getJSONObject("displayImage~").getJSONArray("elements")
                            .getJSONObject(0).getJSONArray("identifiers").getJSONObject(0).getString("identifier");
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogManagement.addLog(TAG,""+e.getMessage());
                }

                String profilePictureUrl = null;
                try {
                    profilePictureUrl = json_data.getJSONObject("profilePicture").getJSONObject("displayImage~").getJSONArray("elements")
                            .getJSONObject(2).getJSONArray("identifiers").getJSONObject(0).getString("identifier");
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogManagement.addLog(TAG,""+e.getMessage());
                }

                user = new UsersRecord();
                user.setTokenLinkedin(accesToken);
                user.setIdLinkedin(idLinkedIn);
                user.setName(name);
                user.setSurname(surname);
                user.setLanguage(language);
                user.setPictureUrl(profilePictureUrl);
                user.setThumbUrl(thumbPictureUrl);

                user.setEmail(getEmailFromLinkedIn(accesToken));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            LogManagement.addLog(TAG,""+e.getMessage());
        }
        return user;
    }

    private String getEmailFromLinkedIn(String accesToken) {
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
}