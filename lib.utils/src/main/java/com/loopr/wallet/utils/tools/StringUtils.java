package com.loopr.wallet.utils.tools;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by snow on 2018/3/6.
 */

public class StringUtils {

    public static String getHumanReadSize(long size) {
        if (size < 1024) {
            return size + "B";
        } else if (size < 1024 * 1024) {
            float sizeInKB = ((float) size) / 1024;
            DecimalFormat format = new DecimalFormat("#.0");
            String sizeString = format.format(sizeInKB);
            return sizeString + "KB";
        } else if (size < 1024L * 1024 * 1024) {
            float sizeInKB = ((float) size) / (1024 * 1024);
            DecimalFormat format = new DecimalFormat("#.0");
            String sizeString = format.format(sizeInKB);
            return sizeString + "MB";
        } else {
            float sizeInKB = ((float) size) / (1024L * 1024 * 1024);
            DecimalFormat format = new DecimalFormat("#.0");
            String sizeString = format.format(sizeInKB);
            return sizeString + "GB";
        }
    }

    /**
     * Return a random string with x length. This string composed by alpha and
     * number.
     *
     * @param length
     * @return random string.
     */
    public static String getRandomString(int length) {
        String baseString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer randomStringBuff = new StringBuffer();
        for (int i = 0; i < length; i++) {
            randomStringBuff.append(baseString.charAt(random.nextInt(baseString
                    .length())));
        }
        return randomStringBuff.toString();
    }

    public static boolean isValidUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }

        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return match(regex, url);
    }

    public static String safeFormat(String format, Object... args) {
        try {
            return String.format(format, args);
        } catch (Exception e) {
            return format;
        }
    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
