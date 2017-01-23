package com.sz21c.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {

    public static LocalDateTime getMidnightToday() {
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime getMidnightYesterday() {
        return getMidnightToday().minusDays(1);
    }

    public static String getYestdayForKorean() {
        String timeStr = getMidnightYesterday().getYear() + "년 " + getMidnightYesterday().getMonthValue() + "월 " + getMidnightYesterday().getDayOfMonth() + "일";
        return timeStr;
    }

    public static Date getDateForMidnightToday() {
        return Date.from(getMidnightToday().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getDateForMidnightYesterday() {
        return Date.from(getMidnightYesterday().atZone(ZoneId.systemDefault()).toInstant());
    }
}
