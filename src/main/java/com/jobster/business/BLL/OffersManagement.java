package com.jobster.BLL;

import com.jobster.BLL.DTO.RespuestaWSOffer;
import com.jobster.BLL.DTO.RespuestaWSOfferCity;
import com.jobster.BLL.DTO.RespuestaWSOfferFilters;
import com.jobster.server.model.tables.records.CompaniesRecord;
import com.jobster.server.model.tables.records.OffersRecord;
import com.jobster.server.util.Fechas;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.List;

import static com.jobster.server.model.Tables.COMPANIES;
import static com.jobster.server.model.Tables.OFFERS;
import static org.jooq.impl.DSL.*;

public class OffersManagement {
    public static String addOffer(String position, String summary, int experience, String job_functions, String date_init, String date_end) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        OffersRecord offer = connection.create.newRecord(OFFERS);
        offer.setPosition(position);
        offer.setSummary(summary);
        offer.setExperience(experience);
        offer.setJobFunctions(job_functions);
        offer.setDateInit(Fechas.GetCurrentTimestampLong());
        offer.setDateEnd(Fechas.GetCurrentTimestampLong());

        offer.store();
        connection.closeConnection();
        return "OK";
    }

    public static List<RespuestaWSOffer> getAllOffers(String keyword, String city) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        Result<Record> result = connection.create.select().from(OFFERS)
                .where(OFFERS.POSITION.contains(keyword).and(OFFERS.CITY.contains(city))).fetch();

        List<RespuestaWSOffer> listOffers = getOffers(connection.create, result);

        connection.closeConnection();
            return listOffers;
    }

    public static boolean offerExist(DSLContext create, int id_offer) {
        OffersRecord offer = create.select()
                .from(OFFERS)
                .where(OFFERS.ID_OFFER.equal(id_offer))
                .fetchAnyInto(OffersRecord.class);

        return offer != null;
    }

    public static List<String> getAllOffersCities() throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        List<String> list = connection.create.selectDistinct(OFFERS.CITY).from(OFFERS).fetchInto(String.class);

        connection.closeConnection();
        return list;
    }

    public static List<RespuestaWSOfferCity> getAllCities() throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        List<RespuestaWSOfferCity> list = connection.create.select(OFFERS.CITY, count()).from(OFFERS).groupBy(OFFERS.CITY).fetchInto(RespuestaWSOfferCity.class);

        connection.closeConnection();
        return list;
    }

    public static List<RespuestaWSOffer> getTopOffers() throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        Result<Record> result = connection.create.select().from(OFFERS).orderBy(OFFERS.PRIORITY.desc()).limit(2).fetch();
        List<RespuestaWSOffer> listOffers = getOffers(connection.create, result);

        connection.closeConnection();
        return listOffers;
    }

    private static List<RespuestaWSOffer> getOffers(DSLContext create, Result<Record> result) {
        List<RespuestaWSOffer> listOffers = new ArrayList<>();
        for (Record r : result) {
            CompaniesRecord company = CompaniesManagement.getCompanyRecord(create, r.getValue(OFFERS.ID_COMPANY));

            RespuestaWSOffer offer = new RespuestaWSOffer(r.getValue(OFFERS.ID_OFFER), company.getValue(COMPANIES.NAME),
                    company.getValue(COMPANIES.PATH_IMG), r.getValue(OFFERS.POSITION), r.getValue(OFFERS.SUMMARY),
                    r.getValue(OFFERS.CITY),  r.getValue(OFFERS.REWARD), r.getValue(OFFERS.SALARY_MIN),
                    r.getValue(OFFERS.SALARY_MAX), r.getValue(OFFERS.DATE_INIT), r.getValue(OFFERS.DATE_INIT));
            listOffers.add(offer);
        }
        return listOffers;
    }

    public static RespuestaWSOfferFilters getAllOffersFilters() throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        Result result = connection.create.select(min(OFFERS.SALARY_MIN), max(OFFERS.SALARY_MAX), max(OFFERS.EXPERIENCE)).from(OFFERS).fetch();

        List<String> list_studies = connection.create.selectDistinct(OFFERS.JOB_FUNCTIONS).from(OFFERS).fetchInto(String.class);

        connection.closeConnection();

        List<RespuestaWSOfferCity> list_cities = getAllCities();

        return new RespuestaWSOfferFilters((Integer) result.getValues(0).get(0), (Integer) result.getValues(1).get(0),
                (Integer) result.getValues(2).get(0), list_studies, list_cities);
    }
}