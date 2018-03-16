package com.loopr.wallet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.loopr.wallet.fragment.TabAssetsFragment;
import com.loopr.wallet.fragment.TabMarketFragment;
import com.loopr.wallet.fragment.TabSettingFragment;
import com.loopr.wallet.fragment.TabTradeFragment;
import com.loopr.wallet.utils.tools.LogUtils;

/**
 * Created by snow on 2018/3/12.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private Class[] classes = new Class[]{TabAssetsFragment.class, TabMarketFragment.class,
        TabTradeFragment.class, TabSettingFragment.class};
    private static final String TAG = "HomeFragmentPagerAdapter";

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Class fragmentClass = classes[position];
        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            return fragment;
        } catch (Exception e) {
            LogUtils.e(TAG,e.getMessage());
        }
        return null;
    }

    @Override
    public int getCount() {
        return classes.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
