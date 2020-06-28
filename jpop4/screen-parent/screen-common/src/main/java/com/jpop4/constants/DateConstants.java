package com.jpop4.constants;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateConstants {

    private DateConstants() {}

    public static final String DATE_FORMAT_MM_DD_YY_HH_MM_SS = "MM/dd/yy HH:mm:ss";
    public static final ZoneId ZONE_UTC = ZoneId.of("UTC");

    public static final DateTimeFormatter DATE_FORMATTER_MMDDYY_HMMSS =
            DateTimeFormatter.ofPattern(DATE_FORMAT_MM_DD_YY_HH_MM_SS).withZone(ZONE_UTC);
}
