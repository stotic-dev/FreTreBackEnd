package org.stotic.dev.com.utilities;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtility {
    public static Long dateToUtcSecTimestamp(Instant date) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(date, ZoneOffset.UTC);
        return zonedDateTime.toEpochSecond();
    }
}
