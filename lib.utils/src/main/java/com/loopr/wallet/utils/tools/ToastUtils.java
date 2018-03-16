package com.loopr.wallet.utils.tools;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by snow on 2018/3/6.
 */
public class ToastUtils {
    static Toast toast;

    public static void showShortToast(Context context, String testStr) {
        if (context != null) {
            Toast.makeText(context, testStr, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShortToast(Context context, int testid) {

        if (context != null) {
            if (toast == null) {
                toast = Toast.makeText(context, testid, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                toast.setText(testid);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }

    public static void showLongToast(Context context, String testStr) {
        if (TextUtils.isEmpty(testStr)) return;
        if (context != null) {
            Toast.makeText(context, testStr, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLongToast(Context context, int testid) {
        if (testid == 0) return;
        if (context != null) {
            Toast.makeText(context, testid, Toast.LENGTH_LONG).show();
        }
    }

    public static void showToastTop(Context context, String toastString) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        toast.setText(toastString);
        toast.setGravity(Gravity.TOP, 0, (int) ScreenUtils.dip2px(context, 100f));
        toast.show();
    }

    public static void showToastTop(Context context, int id) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        toast.setText(id);
        toast.setGravity(Gravity.TOP, 0, (int) ScreenUtils.dip2px(context, 100f));
        toast.show();
    }
}
