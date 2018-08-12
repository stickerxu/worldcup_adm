package com.worldcup.adm.util;

import org.joda.time.DateTime;

import java.util.Date;

public class DateTimeUtil {
    public final static String FORMAT_Y = "yyyy";
    public static final String FORMAT_YM = "yyyyMM";
    public final static String FORMAT_YMD = "yyyyMMdd";
    public final static String FORMAT_YMD_BSLASH = "yyyy/MM/dd";
    public static final String FORMAT_Y_M= "yyyy-MM";
    public static final String FORMAT_Y_M_D= "yyyy-MM-dd";
    public static final String FORMAT_Y_M_D_B_H_M_S= "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_Y_M_D_B_0_0_0 = "yyyy-MM-dd 00:00:00";
    public final static String FORMAT_Y_M_D_B_23_59_59 = "yyyy-MM-dd 23:59:59";
    public static final String FORMAT_YYMD = "yyMMdd";
    public static final String FORMAT_YMDHMS= "yyyyMMddHHmmss";
    public static final String FORMAT_YMDHMSS= "yyyyMMddHHmmssSSS";
    public static final String FORMAT_YMD_CN= "yyyy年MM月dd日";
    public static final String FORMAT_YMD_H_M_CN= "yyyy年MM月dd HH:mm";
    public static final String FORMAT_MDHM_ZN= "MM月dd日HH时mm分";

    public static Date now() {
        return DateTime.now().toDate();
    }

    public static String format(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }

    public static Date plusDays(Date date, int number) {
        if (date == null) {
            return null;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(number).toDate();
    }

    public static Date plusWeeks(Date date, int number) {
        if (date == null) {
            return null;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(number).toDate();
    }

    public static int compareTo(Date firstDate, Date secondDate) {
        return firstDate.compareTo(secondDate);
    }
}
