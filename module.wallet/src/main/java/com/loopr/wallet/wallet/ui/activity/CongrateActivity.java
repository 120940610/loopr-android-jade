package com.loopr.wallet.wallet.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.common.AppGlobal;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.common.utils.LogUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Wallet;
import com.loopr.wallet.wallet.util.KeyUtil;
import com.loopr.wallet.wallet.viewmodel.WalletsViewModel;
import com.loopr.wallet.wallet.viewmodel.WalletsViewModelFactory;

import java.security.SecureRandom;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import dagger.android.AndroidInjection;
import io.reactivex.Single;

/**
 * Created by snow on 2018/4/19.
 */
@Route(path = "/wallet/CongrateActivity")
public class CongrateActivity extends BaseActivity{
    @BindView(R2.id.title_bar)
    public CommonTitleBar commonTitleBar;

    @BindView(R2.id.wallet_backup)
    public TextView mWalletBackup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_congrate_activity);
        ButterKnife.bind(this);

        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @OnClick(R2.id.wallet_backup)
    public void onNextClick(TextView textView){
        ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
    }

}
