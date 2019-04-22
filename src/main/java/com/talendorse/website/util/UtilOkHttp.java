package com.talendorse.website.util;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class UtilOkHttp {

    public static OkHttpClient getClientHTTP() {
        return new OkHttpClient();
    }

    public static OkHttpClient getClientHTTPTimeOut() {
        return new OkHttpClient.Builder()
                .connectTimeout(90,TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();
    }
}