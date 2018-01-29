package com.ejar.onebuy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018\1\17 0017.
 */

public class CurrentTimeUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) throws ParseException {
        synchronized (sdf) {
            return sdf.format(date);
        }
    }

    public static Date parse(long time) throws ParseException {
        return sdf.parse(sdf.format(time));
    }

    public static long getCurrentTime(long time) throws ParseException {
        return sdf.parse(sdf.format(time)).getTime() / 1000;
    }

    public static String  parseTime(long time) throws ParseException {
        return sdf.format(new Date(time));
    }

}
