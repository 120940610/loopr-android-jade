package com.loopr.wallet.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by snow on 18/3/11.
 *
 * Global Context
 */

public class AppGlobal {
    @SuppressLint("StaticFieldLeak")
    private static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
    }

    public static Context getContext() {
        return mApplication.getApplicationContext();
    }

    public static Application getApplication() {
        return mApplication;
    }

}
