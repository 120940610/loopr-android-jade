package com.loopr.wallet;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.common.AppGlobal;
import com.loopr.wallet.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;

/**
 * Created by snow on 2018/3/11.
 */

public class MainApplication extends MultiDexApplication implements HasActivityInjector{

    private boolean mHasAttachBaseContext;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
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
        //Realm.init(this);
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
