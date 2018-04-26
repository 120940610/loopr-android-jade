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
 * Created by snow on 2018/3/29.
 */
@Route(path = "/wallet/PasswdConfirmActivity")
public class PasswdConfirmActivity extends BaseActivity{
    @BindView(R2.id.title_bar)
    public CommonTitleBar commonTitleBar;

    @BindView(R2.id.wallet_confirm)
    public EditText mWalletConfirm;

    @BindView(R2.id.wallet_confirm_line)
    public View mWalletConfirmLine;


    @BindView(R2.id.wallet_confirm_next)
    public ImageView mWalletNext;

    @BindView(R2.id.wallet_confirm_warn)
    public TextView mWalletConfirmWarn;

    @BindString(R2.string.wallet_empty)
    String mWalletEmpty;

    @BindString(R2.string.wallet_passwd_confirm_warn)
    String mWalletPasswdWarn;

    @BindColor(R2.color.wallet_passwd_weak_color)
    int mPasswdWarnColor;

    @Inject
    WalletsViewModelFactory walletsViewModelFactory;
    WalletsViewModel viewModel;

    static final ButterKnife.Setter<View, Boolean> STATUS = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            if(value){
                view.setBackgroundColor(AppGlobal.getContext().getResources().getColor(R.color.black));
            }else {
                view.setBackgroundColor(AppGlobal.getContext().getResources().getColor(R.color.wallet_edittext_custom));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_passwd_confirm_activity);
        ButterKnife.bind(this);

        ButterKnife.apply(mWalletConfirmLine,STATUS, !TextUtils.isEmpty(mWalletConfirm.getText()));
        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewModel= ViewModelProviders.of(this,walletsViewModelFactory).get(WalletsViewModel.class);
        viewModel.createdWallet().observe(this,this::onCreatedWallet);
    }

    private void onCreatedWallet(Wallet wallet) {
        LogUtils.d("Create Wallet :",wallet.address);
    }


    @OnClick(R2.id.wallet_confirm_next)
    public void onNextClick(ImageView imageView){
        if(TextUtils.isEmpty(mWalletConfirm.getText())){
            mWalletConfirmWarn.setText(mWalletEmpty);
            return;
        }
        String confirm=mWalletConfirm.getText().toString().trim();
        if(!confirm.equals(KeyUtil.passwd)){
            mWalletConfirmWarn.setText(mWalletPasswdWarn);
            mWalletConfirmWarn.setTextColor(mPasswdWarnColor);
        }

        viewModel.newWallet();
        ARouter.getInstance().build("/wallet/CongrateActivity").navigation();
    }

    @OnTextChanged(value = R2.id.wallet_confirm, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onWalletNameAfterTextChanged(Editable s) {
        ButterKnife.apply(mWalletConfirmLine,STATUS, !TextUtils.isEmpty(s.toString()));
        if(TextUtils.isEmpty(s.toString())){
            mWalletConfirmWarn.setText("");
        }
    }

}
