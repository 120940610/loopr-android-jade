package com.loopr.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.loopr.wallet.fragment.BaseFragment;
import com.loopr.wallet.handler.FragmentHandler;
import com.loopr.wallet.utils.tools.LogUtils;

/**
 * Created by snow on 2018/3/6.
 */
public class BaseActivity extends AppCompatActivity{
    protected FragmentHandler fragmentHandler = FragmentHandler.NULL_HANDLER;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentHandler = new FragmentHandler(getSupportFragmentManager());
    }

    @Override
    public void onBackPressed()
    {
        boolean fragmentHandled = false;
        Fragment currentFragment = getFragmentHandler().getCurrentFragment();
        if(currentFragment!=null && currentFragment instanceof BaseFragment) {
            fragmentHandled = ((BaseFragment)currentFragment).onBackPressed();
        }

        if(!fragmentHandled)
        {
            try {
                super.onBackPressed();
            } catch (Exception e) {
                LogUtils.e("onBackPressed",e.getMessage());
            }
        }
    }

    public FragmentHandler getFragmentHandler() {
        return fragmentHandler;
    }
}
