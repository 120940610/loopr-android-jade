package com.loopr.wallet.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.loopr.wallet.R;
import com.loopr.wallet.fragment.BaseFragment;
import com.loopr.wallet.fragment.TabAssetsFragment;
import com.loopr.wallet.fragment.TabMarketFragment;
import com.loopr.wallet.fragment.TabSettingFragment;
import com.loopr.wallet.fragment.TabTradeFragment;
import com.loopr.wallet.utils.events.GlobalEvents;
import com.loopr.wallet.utils.tools.AppUtils;
import com.loopr.wallet.utils.tools.LogUtils;
import com.loopr.wallet.view.indicator.FragmentTabPageIndicator;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by snow on 2018/3/6.
 */
public class MainActivity extends BaseActivity {

    //public ViewPager mViewPager;
    //private HomeFragmentPagerAdapter mPagerAdapter;
    private FragmentTabPageIndicator indicator;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        registerFragments();
        //initViewPager();
        initIndicator();
    }

    private void registerFragments() {
        //four tabs of the home page
        getFragmentHandler().registerFragment(TabAssetsFragment.class, R.id.fragment_tab_container, false);
        getFragmentHandler().registerFragment(TabMarketFragment.class, R.id.fragment_tab_container, false);
        getFragmentHandler().registerFragment(TabTradeFragment.class, R.id.fragment_tab_container, false);
        getFragmentHandler().registerFragment(TabSettingFragment.class, R.id.fragment_tab_container, false);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                try {
                    if (fragmentHandler != null && fragmentHandler.getCurrentFragment() != null) {
                        ((BaseFragment) fragmentHandler.getCurrentFragment()).onFragmentSwitched(MainActivity.this);
                    }
                } catch (Exception ex) {
                    LogUtils.e(TAG, ex.getMessage());
                }
            }
        });
    }

    /*private void initViewPager() {
        if (mViewPager == null) {
            mViewPager = (ViewPager) findViewById(R.id.fragment_tab_container);
            mViewPager.setOffscreenPageLimit(3);
            mPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(mPagerAdapter);
            initIndicator();
        }
    }*/


    private void initIndicator() {
        indicator = (FragmentTabPageIndicator) findViewById(R.id.fragment_tab_indicator);
        //indicator.setViewPager(mViewPager);
        indicator.setOnTabSelectedListener(new FragmentTabPageIndicator.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                AppUtils.hideSoftKeyboard(MainActivity.this);
                if (index == FragmentTabPageIndicator.TAB_ASSETS) {
                    fragmentHandler.switchToFragment(TabAssetsFragment.class, false, MainActivity.this);
                } else if (index == FragmentTabPageIndicator.TAB_MARKET) {
                    fragmentHandler.switchToFragment(TabMarketFragment.class, false, MainActivity.this);
                } else if (index == FragmentTabPageIndicator.TAB_TRANSACTION ) {
                    fragmentHandler.switchToFragment(TabTradeFragment.class, false, MainActivity.this);
                } else if (index == FragmentTabPageIndicator.TAB_SETTING) {
                    fragmentHandler.switchToFragment(TabSettingFragment.class, false, MainActivity.this);
                }
            }
        });
        //default page show
        fragmentHandler.switchToFragment(TabAssetsFragment.class, false, MainActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(GlobalEvents.SwitchToFragment event) {
        if (event == null)  return;
        LogUtils.d("switch", "GlobalEvents.SwitchToFragment " + event.fragmentClass.getSimpleName());
        //initViewPager();
        getFragmentHandler().switchToFragment(event.fragmentClass, event.addToBackStack,
                event.enterAnimation, event.exitAnimation, event.popEnterAnimation,
                event.popExitAnimation, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(GlobalEvents.ShowMainTab event) {
        if (event == null)  return;
        LogUtils.e("switch", " GlobalEvents.ShowMainTab");
        if (indicator != null) {
            indicator.setCurrentItem(FragmentTabPageIndicator.TAB_ASSETS);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
