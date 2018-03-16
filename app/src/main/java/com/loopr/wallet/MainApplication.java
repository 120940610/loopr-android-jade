package com.loopr.wallet;

import android.app.Application;
import android.content.Context;

import com.loopr.wallet.utils.AppGlobal;

/**
 * Created by snow on 2018/3/11.
 */

public class MainApplication extends Application {

    private boolean mHasAttachBaseContext;

    @Override
    protected void attachBaseContext(Context base) {
        if (mHasAttachBaseContext) {
            return;
        }
        mHasAttachBaseContext = true;
        super.attachBaseContext(base);
        //Global Context init
        AppGlobal.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
