package com.loopr.wallet.common.ui.fragment;

import android.support.v4.app.Fragment;

import com.loopr.wallet.common.ui.activity.BaseActivity;

/**
 * Created by snow on 2018/3/12.
 */
public class BaseFragment extends Fragment{

	public boolean onBackPressed() {
		return false;
	}

	public void onFragmentSwitched(BaseActivity activity){
	}
}
