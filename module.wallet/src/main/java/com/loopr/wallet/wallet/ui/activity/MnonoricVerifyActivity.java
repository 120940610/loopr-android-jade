package com.loopr.wallet.wallet.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by snow on 2018/5/27.
 */
@Route(path = "/wallet/MnonoricVerifyActivity")
public class MnonoricVerifyActivity extends BaseActivity{

    @BindView(R2.id.title_bar)
    public CommonTitleBar commonTitleBar;

    @BindView(R2.id.wallet_mn_verify_tip)
    public TextView mWalletVerifyTip;

    @BindView(R2.id.wallet_mn_verify_word1)
    public TextView mWalletVerifyWord1;

    @BindView(R2.id.wallet_mn_verify_word2)
    public TextView mWalletVerifyWord2;

    @BindView(R2.id.wallet_mn_verify_word3)
    public TextView mWalletVerifyWord3;

    @BindView(R2.id.wallet_mn_verify_word4)
    public TextView mWalletVerifyWord4;

    @BindDrawable(R2.drawable.wallet_rectangle_black_bg)
    Drawable mWalletBlackBg;

    @BindDrawable(R2.drawable.wallet_rectangle_white_bg)
    Drawable mWalletWhiteBg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_mn_verify_activity);
        ButterKnife.bind(this);

        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @OnClick(R2.id.wallet_mn_verify_word1)
    public void onVerifyWord1Click(TextView textView){
        //ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
    }

    @OnClick(R2.id.wallet_mn_verify_word2)
    public void onVerifyWord2Click(TextView textView){
        //ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
    }

    @OnClick(R2.id.wallet_mn_verify_word3)
    public void onVerifyWord3Click(TextView textView){
        //ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
    }

    @OnClick(R2.id.wallet_mn_verify_word4)
    public void onVerifyWord4Click(TextView textView){
        //ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
    }

}
