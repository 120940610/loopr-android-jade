package com.loopr.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.R;
import com.loopr.wallet.common.ui.activity.BaseActivity;

/**
 * Created by snow on 2018/3/14.
 */

public class SplashActivity extends BaseActivity implements View.OnClickListener{

    private TextView mCreateWalletTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mCreateWalletTextView=(TextView)findViewById(R.id.create_wallet);
        mCreateWalletTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.create_wallet){
            ARouter.getInstance().build("/wallet/CreateWalletActivity").navigation();
        }
    }
}
