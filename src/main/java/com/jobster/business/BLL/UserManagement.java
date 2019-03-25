package com.jobster.business.BLL;

import com.jobster.business.DTO.RespuestaWSAllInfoUser;
import com.jobster.business.DTO.RespuestaWSLogin;
import com.jobster.business.DTO.RespuestaWSOfferUser;
import com.jobster.business.DTO.RespuestaWSUser;
import com.jobster.server.model.tables.records.CompaniesRecord;
import com.jobster.server.model.tables.records.OffersRecord;
import com.jobster.server.model.tables.records.UsersRecord;
import com.jobster.business.types.JobsterErrorType;
import com.jobster.server.util.*;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import static com.jobster.server.model.Tables.*;

public class UserManagement {

    private static int MIN_LEN_PWD = 8;

    public static RespuestaWSLogin Login(String username, String password) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        username = username.toLowerCase();
        UsersRecord usuario = connection.create.select()
                .from(USERS)
                .where(USERS.EMAIL.equal(username))
                .fetchAnyInto(UsersRecord.class);
        if (usuario == null) throw new JobsterException(JobsterErrorType.USER_NOT_FOUND);
        String pwd = usuario.getPassword().toLowerCase();
        String salt = usuario.getSalt().toLowerCase();
        String hashCompletodesdeBD = Seguridad.GenerarSHA56(pwd + salt);
        if (password.toLowerCase().equals(hashCompletodesdeBD))
            throw new JobsterException(JobsterErrorType.LOGIN_ERROR);
        if (usuario.getVerifiedEmail() == null || usuario.getVerifiedEmail() == 0
                || usuario.getVerifiedPhoneNumber() == null || usuario.getVerifiedPhoneNumber() == 0)
            throw new JobsterException(JobsterErrorType.USER_NOT_FOUND);
        RespuestaWSLogin respuestaWSUsuario = new RespuestaWSLogin(usuario.getApikey(), usuario.getPictureUrl(), usuario.getEmail(),
                usuario.getName(), usuario.getSurrname());

        connection.closeConnection();
        return respuestaWSUsuario;
    }

    public static String InsertarUsuario(String email, String password, String name, String surname, String birthday, String gender,
                                         String salt, String idioma, String telefono, String url) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        email = email.trim();
        password = password.trim();
        name = name.trim();
        surname = surname.trim();
        ValidacionParametros(email, password, name, surname, salt);
        String emailEncriptado = EncriptarEmailoTelefono(email.toLowerCase());
        UsersRecord usuario = connection.create.select()
                .from(USERS)
                .where(USERS.EMAIL.equal(emailEncriptado))
                .fetchAnyInto(UsersRecord.class);

        if (usuario != null) throw new JobsterException(JobsterErrorType.USER_ALREADY_EXISTS);

        UsersRecord usr = connection.create.newRecord(USERS);

        usr.setEmail(emailEncriptado);
        usr.setApikey(UUID.randomUUID().toString());
        usr.setName(name);
        usr.setSurrname(surname);
        usr.setPictureUrl("/Upload/User/" + Seguridad.GenerateSecureRandomString() + "/" + Seguridad.GenerarRandomFileName() + "_thumb.jpg");
        usr.setDateBirthday(Fechas.GetCurrentTimestampLong());
        usr.setPassword(password);
        usr.setSalt(salt);//Seguridad.GenerarSHA56(String.valueOf(Integer.parseInt(salt) * 8)));
        usr.setPhoneNumber(EncriptarEmailoTelefono(telefono));
        usr.setVerifiedPhoneNumber(1);
        usr.setIdiom(idioma);
        usr.setGender(gender);
        usr.store();

//        String subjectCorreoEstablecimiento;
//        if (idioma != null) {
//            if (idioma.toLowerCase().equals("es")) {
//                subjectCorreoEstablecimiento = Constantes.EMAIL_SUBJECT_RECOVER_PASSWORD_ES;
//            } else {
//                // By default language
//                subjectCorreoEstablecimiento = Constantes.EMAIL_SUBJECT_RECOVER_PASSWORD_EN;
//            }
//        } else {
//            // By default language
//            subjectCorreoEstablecimiento = "Bienvenido a Jobster"; //TODO: constantes mail HttpContext.GetGlobalResourceObject("Literales", "subject2", System.Globalization.CultureInfo.CreateSpecificCulture("en")).ToString();
//        }
//
//        try {
//            String enlace = url + "Activate?enlace=" + URLEncoder.encode(EncriptarEnlace(usr.getApikey()), java.nio.charset.StandardCharsets.UTF_8.toString());
//            String textoEmail = TextoMail(enlace + "&lang=" + idioma, "mail/Activate.aspx", url, "&lang=" + idioma, usr.getUserName());
//
//            Email.sendEmail(usr.getEmail(), subjectCorreoEstablecimiento, textoEmail);
//        } catch (UnsupportedEncodingException e) {
//            throw new JobsterException(JobsterErrorType.ENCRYPTING_ERROR);
//        }

        connection.closeConnection();
        return usr.getApikey();
    }


    public static String insertarUsuario_temp(String name, String surname, String password, String gender, String email, String url)  throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();
        UsersRecord usr = connection.create.newRecord(USERS);

        usr.setName(name);
        usr.setSurrname(surname);
        usr.setEmail(email.trim());
        usr.setGender(gender);
        usr.setPassword(password);
        usr.setIdiom("es");
        usr.setApikey(UUID.randomUUID().toString());
        usr.setValidationToken(UUID.randomUUID().toString());
        usr.setPictureUrl("/Upload/User/" + Seguridad.GenerateSecureRandomString() + "/" + Seguridad.GenerarRandomFileName() + "_thumb.jpg");
        usr.setDateBirthday(Fechas.GetCurrentTimestampLong());
        usr.setSalt("trnmnmfysxmzxf");
        usr.setPhoneNumber("+34658632698"); //EncriptarEmailoTelefono("+34658632698")); //TODO: encriptar telefono
        usr.setVerifiedPhoneNumber(0);
        usr.store();

        String url_location = Constantes.URL_EMAIL_VALIDATION_ACCOUNT_URL_ES;
        String email_subject = Constantes.EMAIL_SUBJECT_USER_ACTIVATION_ES;

        String textoEmail = TextoMail(url, url_location);
        textoEmail = textoEmail.replace("user_name_jobster", usr.getName());
        textoEmail = textoEmail.replace("url_jobster_validation", url+
                "jobster/email/account_activated.html?activation_token="+ usr.getValidationToken());

        Email.sendEmail(email, email_subject, textoEmail);

        return "OK";
    }

    private static void ValidacionParametros(String email, String password, String name, String surname, String salt) throws JobsterException {

        if (!ValidarTipos.EsEmailValido(email))
            throw new JobsterException(JobsterErrorType.INVALID_MAIL_FORMAT);

        if (!ValidarTipos.ValidatePassword(password))
            throw new JobsterException(JobsterErrorType.INVALID_PASSWORD_FORMAT);

        if ((name == null) || (name.isEmpty()))
            throw new JobsterException(JobsterErrorType.INVALID_NAME);

        if ((surname == null) || (surname.isEmpty()))
            throw new JobsterException(JobsterErrorType.INVALID_SURNAME);

        if (password.length() <= (UserManagement.MIN_LEN_PWD - 1))
            throw new JobsterException(JobsterErrorType.PASSWORD_TOO_SHORT);
    }

    public static String EncriptarEnlace(String apiKey) throws JobsterException {
        CriptografiaSimetrica criptografiaSimetrica = new CriptografiaSimetrica(CriptografiaSimetrica.CryptoProvider.AES);
        criptografiaSimetrica.setKey(Constantes.CLAVE_ENCRIPTACION);
        criptografiaSimetrica.setIV(Constantes.VI_ENCRIPTACION);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String enlace = calendar.get(Calendar.YEAR) + Constantes.PATHERN_SPLIT +
                apiKey + Constantes.PATHERN_SPLIT +
                calendar.get(Calendar.MONTH) + Constantes.PATHERN_SPLIT +
                calendar.get(Calendar.DAY_OF_MONTH) + Constantes.PATHERN_SPLIT +
                calendar.get(Calendar.HOUR) + Constantes.PATHERN_SPLIT +
                UUID.randomUUID().toString() + Constantes.PATHERN_SPLIT +
                calendar.get(Calendar.MINUTE);

        return Base64.getEncoder().encodeToString(criptografiaSimetrica.encriptar(enlace));
    }

    public static String TextoMail(String url, String url_location) throws JobsterException {
        try {
            return GetURLContent(url, url_location);
        } catch (Exception e) {
            throw new JobsterException(JobsterErrorType.GENERIC_ERROR);
        }
    }

    private static String GetURLContent(String url_header, String url_location) throws JobsterException {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(url_header+url_location);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            throw new JobsterException(JobsterErrorType.GENERIC_ERROR);
        }
        return content.toString();
    }

    public static UsersRecord GetUserfromApiKey(String apiKey) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        UsersRecord usr = connection.create.select()
                .from(USERS)
                .where(USERS.APIKEY.equal(apiKey))
                .fetchAnyInto(UsersRecord.class);

        connection.closeConnection();
        return usr;
    }

    public static RespuestaWSUser UserInformation(String apiKey) throws JobsterException {
        UsersRecord usuario = GetUserfromApiKey(apiKey);
        if (usuario == null) throw new JobsterException(JobsterErrorType.USER_NOT_FOUND);
        String urlThumbnail = usuario.getPictureUrl();
        String urlAvatar = "";
        if ((urlThumbnail != null) && (!urlThumbnail.isEmpty())) {
            urlAvatar = urlThumbnail;
        }
        usuario.setLastConnection(Fechas.GetCurrentTimestampLong());
        usuario.store();
        return new RespuestaWSUser(urlAvatar, usuario.getEmail(), usuario.getName(), usuario.getSurrname(), usuario.getPhoneNumber());
    }

    public static String LogOut(String apiKey) throws JobsterException {
        //Habria que borrar el APIKEY a uno distinto o algo
        UsersRecord usuario = GetUserfromApiKey(apiKey);
        if (usuario == null) throw new JobsterException(JobsterErrorType.USER_NOT_FOUND);
        return "OK";
    }


    private static String EncriptarEmailoTelefono(String data) throws JobsterException {
        CriptografiaSimetrica criptografiaSimetrica = new CriptografiaSimetrica(CriptografiaSimetrica.CryptoProvider.AES);
        criptografiaSimetrica.setKey(Constantes.CLAVE_ENCRIPTACION);
        criptografiaSimetrica.setIV(Constantes.VI_ENCRIPTACION);
        return Base64.getEncoder().encodeToString(criptografiaSimetrica.encriptar(data));
    }

    private static String DesencriptarEmailoTelefono(String dataEnc) throws JobsterException {
        CriptografiaSimetrica criptografiaSimetrica = new CriptografiaSimetrica(CriptografiaSimetrica.CryptoProvider.AES);
        criptografiaSimetrica.setKey(Constantes.CLAVE_ENCRIPTACION);
        criptografiaSimetrica.setIV(Constantes.VI_ENCRIPTACION);
        return criptografiaSimetrica.desencriptar(Base64.getDecoder().decode(dataEnc));
    }

    public static RespuestaWSAllInfoUser getAllInfoUser(Integer idUser) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        UsersRecord usr = getUser(connection, idUser);

        List<Integer> ids_list = connection.create.select().from(USERS_SKILLS).where(USERS_SKILLS.ID_USER.equal(idUser)).fetch(USERS_SKILLS.ID_SKILL);
        List<String> skills_list = connection.create.select().from(SKILLS).where(SKILLS.ID_SKILL.in(ids_list)).fetch(SKILLS.NAME);

        ids_list = connection.create.select().from(USER_IDIOM).where(USER_IDIOM.ID_USER.equal(idUser)).fetch(USER_IDIOM.ID_IDIOM);
        List<String> idioms_list = connection.create.select().from(IDIOMS).where(IDIOMS.ID_IDIOM.in(ids_list)).fetch(SKILLS.NAME);

        connection.closeConnection();
        return new RespuestaWSAllInfoUser(usr, skills_list, idioms_list);
    }

    public static List<RespuestaWSUser> getAllUsers() throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        Result<Record> result = connection.create.select().from(USERS).fetch();

        List<RespuestaWSUser> listUsers = new ArrayList<>();
        for (Record r : result) {
            RespuestaWSUser user = new RespuestaWSUser( r.getValue(USERS.NAME), r.getValue(USERS.SURRNAME),
                    r.getValue(USERS.EMAIL), r.getValue(USERS.PICTURE_URL), r.getValue(USERS.PHONE_NUMBER));
            listUsers.add(user);
        }
        connection.closeConnection();
        return listUsers;
    }

    public static boolean userExist(DSLContext create, int id_user) {
        UsersRecord usr = create.select()
                .from(USERS)
                .where(USERS.ID_USER.equal(id_user))
                .fetchAnyInto(UsersRecord.class);

        return usr != null;
    }

    public static String getIdiom(DSLContext create, int id_user) {
        UsersRecord usr = create.select()
                .from(USERS)
                .where(USERS.ID_USER.equal(id_user))
                .fetchAnyInto(UsersRecord.class);
        return usr.getIdiom();
    }

    public static String validateEmail(String token) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();
        UsersRecord user = getUserToken(connection, token);

        user.setVerifiedEmail(1);
        user.setValidationToken(null);
        user.store();
        connection.closeConnection();
        return "OK";
    }

    public static List<RespuestaWSOfferUser> getAllUserOffers(int idUser) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        List<RespuestaWSOfferUser> listOffers = new ArrayList<>();
        Result<Record> referals = connection.create.select()
                .from(REFERRALS)
                .where(REFERRALS.ID_CANDIDATE.equal(idUser).or(REFERRALS.ID_JOBSTER.equal(idUser)))
                .fetch();

        for (Record ref : referals) {
            OffersRecord off = connection.create.select()
                    .from(OFFERS)
                    .where(OFFERS.ID_OFFER.equal(ref.getValue(REFERRALS.ID_OFFER)))
                    .fetchAnyInto(OffersRecord.class);

            if (off == null) throw new JobsterException(JobsterErrorType.OFFER_NOT_EXISTS);

            UsersRecord jobster = connection.create.select().from(USERS).where(USERS.ID_USER.equal(ref.getValue(REFERRALS.ID_JOBSTER))).fetchAnyInto(UsersRecord.class);
            if (jobster == null) throw new JobsterException(JobsterErrorType.USER_NOT_FOUND);

            CompaniesRecord company = CompaniesManagement.getCompanyRecord(connection.create, off.getValue(OFFERS.ID_COMPANY));

            RespuestaWSOfferUser respues = new RespuestaWSOfferUser(ref.getValue(REFERRALS.ID_REFERRAL), ref.getValue(REFERRALS.ID_OFFER),
                    ref.getValue(REFERRALS.STATE), ref.getValue(REFERRALS.ID_JOBSTER), jobster.getName(),
                    jobster.getSurrname(), ref.getValue(REFERRALS.EMAIL_CANDIDATE), company.getValue(COMPANIES.NAME),
                    company.getValue(COMPANIES.PATH_IMG), off.getPosition(), off.getSummary(), ref.getValue(REFERRALS.DATE_CREATION),
                    ref.getValue(REFERRALS.DATE_ACCEPTED), off.getDateEnd());

            if(ref.getValue(REFERRALS.ID_CANDIDATE) != null) {
                UsersRecord candidate = connection.create.select()
                        .from(USERS)
                        .where(USERS.ID_USER.equal(ref.getValue(REFERRALS.ID_CANDIDATE)))
                        .fetchAnyInto(UsersRecord.class);

                if (candidate == null) throw new JobsterException(JobsterErrorType.CANDIDATE_NOT_FOUND);

                respues.setIdCandidato(ref.getValue(REFERRALS.ID_CANDIDATE));
                respues.setName_candidate(candidate.getName());
                respues.setSurname_candidate(candidate.getSurrname());
            }
            listOffers.add(respues);
        }
        connection.closeConnection();
        return  listOffers;
    }

    public static UsersRecord getUser(ConnectionBDManager connection, int idUser) throws JobsterException {
        UsersRecord user = connection.create.select()
                .from(USERS)
                .where(USERS.ID_USER.equal(idUser))
                .fetchAnyInto(UsersRecord.class);

        if (user == null) throw new JobsterException(JobsterErrorType.USER_NOT_FOUND);
        return user;
    }

    private static UsersRecord getUserToken(ConnectionBDManager connection, String token) throws JobsterException {
        UsersRecord user = connection.create.select()
                .from(USERS)
                .where(USERS.VALIDATION_TOKEN.equal(token))
                .fetchAnyInto(UsersRecord.class);

        if (user == null) throw new JobsterException(JobsterErrorType.TOKEN_NOT_FOUND);
        return user;
    }

    public static String emailValidateUser(String url, int id_user, String idiom) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();
        UsersRecord user = UserManagement.getUser(connection, id_user);

        String url_location;
        String email_subject;

        if(idiom.equals("es")) {
            url_location = Constantes.URL_EMAIL_VALIDATION_ACCOUNT_URL_ES;
            email_subject = Constantes.EMAIL_SUBJECT_USER_ACTIVATION_ES;
        }
        else {
            url_location = Constantes.URL_EMAIL_VALIDATION_ACCOUNT_URL_EN;
            email_subject = Constantes.EMAIL_SUBJECT_USER_ACTIVATION_EN;
        }

        String textoEmail = TextoMail(url, url_location);
        textoEmail = textoEmail.replace("user_name_jobster", user.getName());
        textoEmail = textoEmail.replace("url_jobster_validation", url+
                "jobster/email/account_activated.html?activation_token="+ user.getValidationToken());

        Email.sendEmail(user.getEmail(), email_subject, textoEmail);

        connection.closeConnection();
        return "OK";
    }
}
