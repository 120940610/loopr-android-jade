package com.loopr.wallet.utils.tools;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * @author snow
 */
public class AppUtils {

    public static void hideSoftKeyboard(Context context) {
        if (context == null)
            return;
        try {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }

    }

    public static boolean isSoftKeyboardShowing(Context context) {
        InputMethodManager m = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        return m.isActive();
    }

}
