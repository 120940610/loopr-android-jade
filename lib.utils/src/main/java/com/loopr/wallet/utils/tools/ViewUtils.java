package com.loopr.wallet.utils.tools;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by snow on 2018/3/6.
 */
public class ViewUtils {
    public static void setStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void setStatusBarColor(Activity activity, int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                View rootView = ((ViewGroup) (activity.findViewById(android.R.id.content)))
                        .getChildAt(0);
                if (rootView != null) {
                    rootView.setFitsSystemWindows(true);
                    SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                    tintManager.setStatusBarTintColor(color);
                    tintManager.setStatusBarTintEnabled(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
