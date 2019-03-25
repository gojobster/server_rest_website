package com.jobster.business.BLL;

public class Constantes {
    private static final boolean IS_DEBUG = false;

    private static final String WS_JOBSTER_DEBUG = "http://localhost:8080/";
    private static final String WS_JOBSTER_RELEASE = "http://18.221.163.161:8080/ws/";
    private static final String WS_JOBSTER_URL = IS_DEBUG ? WS_JOBSTER_DEBUG : WS_JOBSTER_RELEASE;

    public static final String DB_PASS_LOCALHOST = "1234";
    public static final String DB_PASS_SERVER = "Jobster2018!";

    public static final String DB_USER = "root";
    public static final String DB_PASS = IS_DEBUG ? DB_PASS_LOCALHOST : DB_PASS_SERVER;
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/jobster?serverTimezone=UTC";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String CLAVE_ENCRIPTACION = "Binomio Alto";
    public static final String VI_ENCRIPTACION = "456";
    public static final String PATHERN_SPLIT= "RFVBGT";

    public static final int NUM_RANDOM_INTENTS_CODE_OFFER = 10;

    public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    /****************************************************************************
     ************************* Web Services ***********************************
     ****************************************************************************/
    public static final String WS_GET_ALL_OFFERS = WS_JOBSTER_URL + "getAllOffers";
    public static final String URL_EMAIL_VALIDATION_ACCOUNT_URL_ES = WS_JOBSTER_URL + "email/email_activation_es.html";
    public static final String URL_EMAIL_SEND_RECOMMENDATION_URL_ES = WS_JOBSTER_URL + "email/email_recommendation_es.html";
    public static final String URL_EMAIL_VALIDATION_ACCOUNT_URL_EN = WS_JOBSTER_URL + "email/email_activation_en.html";
    public static final String URL_EMAIL_SEND_RECOMMENDATION_URL_EN = WS_JOBSTER_URL + "email/email_recommendation_en.html";

    /****************************************************************************
     ************************* EMAIL SETTINGS ***********************************
     ****************************************************************************/

    public static final String SRV_EMAIL_FROM_ACCOUNT = "hello@gojobster.com";
    public static final String SRV_EMAIL_HOST = "smtp-es.securemail.pro";

    public static final String SRV_EMAIL_USR = "hello@gojobster.com";
    public static final String SRV_EMAIL_PWD = "Jobster2018!";
    public static final int SRV_EMAIL_PORT = 465;
    public static final Boolean SRV_EMAIL_ENABLE_SSL = true;


    public static final String EMAIL_SUBJECT_RECOVER_PASSWORD_ES = "Jobster - Recuperar Contraseña";
    public static final String EMAIL_SUBJECT_USER_ACTIVATION_ES = "Jobster - Activar jobster";
    public static final String EMAIL_SUBJECT_OFFER_RECOMENDATION_ES = "Jobster - Recomendación de trabajo de";



    public static final String EMAIL_SUBJECT_RECOVER_PASSWORD_EN = "Jobster - Recover Password";
    public static final String EMAIL_SUBJECT_USER_ACTIVATION_EN = "Jobster - jobster's activation ";
    public static final String EMAIL_SUBJECT_OFFER_RECOMENDATION_EN = "Jobster - Job offer recomendation from ";
}
