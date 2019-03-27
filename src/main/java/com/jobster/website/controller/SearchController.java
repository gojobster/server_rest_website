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

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    public List<RespuestaWSOffer> listOffers;

    @GetMapping("/search")
    public String search(@RequestParam("s") String filter_keyword, Model model) {
        String total = "69";

        getAllOffers(filter_keyword, "");

        model.addAttribute("listOffers", listOffers);
        return "search"; //view
    }

    private void getAllOffers(String filter_keyword, String filter_city) {
        String getUrl = Constantes.WS_GET_ALL_OFFERS;

        try {
            OkHttpClient client = UtilOkHttp.getClientHTTP();
            RequestBody formBody = new FormBody.Builder()
                    .add("filter_keyword", filter_keyword)
                    .add("filter_city", filter_city)
                    .build();

            Request request = new Request.Builder()
                    .url(getUrl)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                String respuesta = response.body().string();

                JSONObject json_data = new JSONObject(respuesta);

                if (json_data.getInt("responseStatus") == 200) {
                    JSONArray listOffersJSON = json_data.getJSONArray("message");

                    listOffers = new ArrayList<>();
                    for(int i=0; i<listOffersJSON.length(); i++) {
                        JSONObject offerJSON = listOffersJSON.getJSONObject(i);
//                      respuesta = URLDecoder.decode(respuesta, "UTF-8");
                        RespuestaWSOffer offer = new RespuestaWSOffer(offerJSON);
                        listOffers.add(offer);
                    }
                }
            }
        }
        catch (Exception ex) {
            String a = ex.getMessage();
        }
    }
}