package com.jobster.business.BLL;

import com.jobster.server.model.tables.records.IdiomsRecord;
import com.jobster.business.types.JobsterErrorType;

import static com.jobster.server.model.Tables.IDIOMS;

public class IdiomsManagement {
    public static String addIdiom(String idiomName) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        idiomName = idiomName.trim();

        IdiomsRecord idiom = connection.create.select()
                .from(IDIOMS)
                .where(IDIOMS.NAME.equal(idiomName))
                .fetchAnyInto(IdiomsRecord.class);
        if (idiom != null) throw new JobsterException(JobsterErrorType.IDIOM_ALREADY_EXISTS);

        idiom = connection.create.newRecord(IDIOMS);
        idiom.setName(idiomName);

        idiom.store();
        connection.closeConnection();
        return "OK";
    }
}
