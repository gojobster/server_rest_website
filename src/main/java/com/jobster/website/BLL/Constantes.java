package com.jobster.website.BLL;

public class Constantes {
    private static final boolean IS_DEBUG = false;

    private static final String WS_JOBSTER_DEBUG = "http://localhost:8085/jobster_server_war/";
    private static final String WS_JOBSTER_RELEASE = "http://18.221.163.161:8080/ws/";
    private static final String WS_JOBSTER_URL = IS_DEBUG ? WS_JOBSTER_DEBUG : WS_JOBSTER_RELEASE;

    public static final String WS_GET_ALL_OFFERS = WS_JOBSTER_URL + "getAllOffers";
}
