package com.loopr.wallet.utils.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by snow on 2018/3/6.
 */

public class TimeUtils {
    private static final String TAG = "TimeUtils";
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final long HOUR = 60 * 60 * 1000;

    public static String long2DateTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    public static String getDuring(long lastTime, long nowTime) {
        return String.valueOf((nowTime - lastTime) / HOUR + 1);
    }
}