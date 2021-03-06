package com.loopr.wallet.wallet.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Constant;
import com.loopr.wallet.wallet.entity.Wallet;
import com.loopr.wallet.wallet.ui.adapter.TabPagerAdapter;
import com.loopr.wallet.wallet.ui.fragment.ImportKeystoreFragment;
import com.loopr.wallet.wallet.ui.fragment.ImportMnemonicFragment;
import com.loopr.wallet.wallet.ui.fragment.ImportPrivateKeyFragment;
import com.loopr.wallet.wallet.viewmodel.ImportWalletViewModel;
import com.loopr.wallet.wallet.viewmodel.ImportWalletViewModelFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by snow on 2018/5/3.
 */
@Route(path = "/wallet/ImportWalletActivity")
public class ImportWalletActivity extends BaseActivity{

    private static final int MNEMONIC_TAB_INDEX = 0;
    private static final int KEYSTORE_TAB_INDEX = 1;
    private static final int PRIVATE_TAB_INDEX = 2;

    @BindString(R2.string.wallet_import_tab_mnemonic)
    String tabMnemonic;

    @BindString(R2.string.wallet_import_tab_keystore)
    String tabKeystore;

    @BindString(R2.string.wallet_import_tab_private)
    String tabPrivate;

    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R2.id.title_bar)
    CommonTitleBar titleBar;

    @Inject
    ImportWalletViewModelFactory importWalletViewModelFactory;
    ImportWalletViewModel importWalletViewModel;

    private final List<Pair<String, Fragment>> pages = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_import_wallet);
        ButterKnife.bind(this);

        pages.add(MNEMONIC_TAB_INDEX, new Pair<>(tabMnemonic, ImportMnemonicFragment.create()));
        pages.add(KEYSTORE_TAB_INDEX, new Pair<>(tabKeystore, ImportKeystoreFragment.create()));
        pages.add(PRIVATE_TAB_INDEX, new Pair<>(tabPrivate, ImportPrivateKeyFragment.create()));

        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), pages));
        tabLayout.setupWithViewPager(viewPager);
        setIndicator(tabLayout,20,20);

        importWalletViewModel = ViewModelProviders.of(this, importWalletViewModelFactory)
                .get(ImportWalletViewModel.class);
        importWalletViewModel.wallet().observe(this, this::onWallet);

        titleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((ImportMnemonicFragment) pages.get(MNEMONIC_TAB_INDEX).second)
                .setOnImportWalletListener(importWalletViewModel);
        ((ImportKeystoreFragment) pages.get(KEYSTORE_TAB_INDEX).second)
                .setOnImportWalletListener(importWalletViewModel);
        ((ImportPrivateKeyFragment) pages.get(PRIVATE_TAB_INDEX).second)
                .setOnImportWalletListener(importWalletViewModel);
    }

    private void onWallet(Wallet wallet) {
        Intent result = new Intent();
        result.putExtra(Constant.Key.WALLET, wallet);
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }


    /**
     * change the Indicator width of the TabLayout
     */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (Resources.getSystem().getDisplayMetrics().density * leftDip);
        int right = (int) (Resources.getSystem().getDisplayMetrics().density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }


}
