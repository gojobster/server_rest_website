package com.jobster.BLL;

import com.jobster.server.model.tables.records.CompaniesRecord;
import org.jooq.DSLContext;

import static com.jobster.server.model.Tables.COMPANIES;

public class CompaniesManagement {
    public static CompaniesRecord getCompanyRecord(DSLContext create, int id_company) {
            return create.select()
                    .from(COMPANIES)
                    .where(COMPANIES.ID_COMPANY.equal(id_company))
                    .fetchAnyInto(CompaniesRecord.class);
    }
}
