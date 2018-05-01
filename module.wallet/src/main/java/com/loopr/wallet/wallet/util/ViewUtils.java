package com.loopr.wallet.wallet.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by snow 2018/5/1.
 */

public class ViewUtils {

    public static void showCommonDialog(final Context context, String messageResId, String cancel, String positive,
                                        DialogInterface.OnClickListener onLeftClickListener, DialogInterface.OnClickListener onRightClickListener) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return;
        }
        new AlertDialog.Builder(context)
                .setPositiveButton(positive, onRightClickListener)
                .setNegativeButton(cancel,onLeftClickListener)
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }
}
