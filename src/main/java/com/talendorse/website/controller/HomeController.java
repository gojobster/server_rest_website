package com.talendorse.website.controller;

import com.talendorse.server.BLL.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String main() {
        return "index"; //view
    }

    private void connectToLinkedIn() {
//        String profileUrl = "https://api.linkedin.com/v2/me/";
        String profileUrl = getAuthorizationUrl();

        // Access token for the r_liteprofile permission
        String accessToken = "JjQyf7QOjHERNgFj";

//        try {
//            JsonObject profileData = sendGetRequest(profileUrl, accessToken);
//            System.out.println(profileData.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

//    private static JsonObject sendGetRequest(String urlString, String accessToken) throws Exception {
//        URL url = new URL(urlString);
//        HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
//
//        con.setRequestMethod("GET");
////        con.setRequestProperty("Authorization", "Bearer " + accessToken);
//        con.setRequestProperty("cache-control", "no-cache");
//        con.setRequestProperty("X-Restli-Protocol-Version", "2.0.0");
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        StringBuilder jsonString = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            jsonString.append(line);
//        }
//        br.close();
//
//        JsonReader jsonReader = Json.createReader(new StringReader(jsonString.toString()));
//        JsonObject jsonObject = jsonReader.readObject();
//
//        return jsonObject;
//    }

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