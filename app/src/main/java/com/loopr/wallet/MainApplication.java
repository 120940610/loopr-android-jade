package com.loopr.wallet;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.common.AppGlobal;
import com.loopr.wallet.di.DaggerAppComponent;

import io.realm.Realm;

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
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        //Global Context init
        AppGlobal.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

    }
}
