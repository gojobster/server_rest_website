package com.jobster.server.util;

import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.TimeZone;

public final class Fechas {
    public static Timestamp GetCurrentTimestampLong() {
        return new Timestamp(new DateTime().getMillis() + TimeZone.getDefault().getRawOffset());
    }
}
