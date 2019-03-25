package com.jobster.business.BLL;

public class Constantes {
    public static final String DB_USER = "root";
    public static final String DB_PASS = "1234";
//    public static final String DB_PASS = "Jobster2018!";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/jobster?serverTimezone=UTC";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String CLAVE_ENCRIPTACION = "Binomio Alto";
    public static final String VI_ENCRIPTACION = "456";
    public static final String PATHERN_SPLIT= "RFVBGT";

    public static final int NUM_RANDOM_INTENTS_CODE_OFFER = 10;

    public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";


    /****************************************************************************
     ************************* EMAIL SETTINGS ***********************************
     ****************************************************************************/

    public static final String SRV_EMAIL_FROM_ACCOUNT = "hello@gojobster.com";
    public static final String SRV_EMAIL_HOST = "smtp-es.securemail.pro";

    public static final String SRV_EMAIL_USR = "hello@gojobster.com";
    public static final String SRV_EMAIL_PWD = "Jobster2018!";
    public static final int SRV_EMAIL_PORT = 465;
    public static final Boolean SRV_EMAIL_ENABLE_SSL = true;



    public static final String EMAIL_VALIDATION_ACCOUNT_URL_ES = "jobster/email/email_activation_es.html";
    public static final String EMAIL_SEND_RECOMMENDATION_URL_ES = "jobster/email/email_recommendation_es.html";
    public static final String EMAIL_SUBJECT_RECOVER_PASSWORD_ES = "Jobster - Recuperar Contraseña";
    public static final String EMAIL_SUBJECT_USER_ACTIVATION_ES = "Jobster - Activar jobster";
    public static final String EMAIL_SUBJECT_OFFER_RECOMENDATION_ES = "Jobster - Recomendación de trabajo de";


    public static final String EMAIL_VALIDATION_ACCOUNT_URL_EN = "jobster/email/email_activation_en.html";
    public static final String EMAIL_SEND_RECOMMENDATION_URL_EN = "jobster/email/email_recommendation_en.html";
    public static final String EMAIL_SUBJECT_RECOVER_PASSWORD_EN = "Jobster - Recover Password";
    public static final String EMAIL_SUBJECT_USER_ACTIVATION_EN = "Jobster - jobster's activation ";
    public static final String EMAIL_SUBJECT_OFFER_RECOMENDATION_EN = "Jobster - Job offer recomendation from ";
}
