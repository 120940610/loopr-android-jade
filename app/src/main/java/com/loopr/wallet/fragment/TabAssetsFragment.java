package com.loopr.wallet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopr.wallet.R;
import com.loopr.wallet.utils.tools.ToastUtils;

/**
 * Created by snow on 2018/3/12.
 */

public class TabAssetsFragment extends BaseFragment{
    private View mRootView;
    private long mExitTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_tab_assets, null, false);
        return mRootView;
    }

    @Override
    public boolean onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShortToast(getActivity(),R.string.press_again_and_exit);
            mExitTime = System.currentTimeMillis();
        } else {
            getActivity().finish();
        }
        return true;
    }
}
