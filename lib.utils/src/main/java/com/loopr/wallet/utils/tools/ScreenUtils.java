package com.loopr.wallet.utils.tools;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 *
 * UI tools commonly used
 * 
 * @author snow
 *
 */
public class ScreenUtils {

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenWidth(Resources res) {
        DisplayMetrics dm = res.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getScreenHeight(Resources res) {
        DisplayMetrics dm = res.getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * dip2px
     * 
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        if (context != null && context.getResources() != null) {
            DisplayMetrics display = context.getResources().getDisplayMetrics();
            if (display != null) {
                final float scale = display.density;
                return (int) (dipValue * scale + 0.5f);
            }
        }
        return 0;
    }

    /**
     * px2dip.
     * 
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px2sp
     * 
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp2px
     * 
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
