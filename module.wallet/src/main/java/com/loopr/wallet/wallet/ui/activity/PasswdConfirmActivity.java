package com.loopr.wallet.wallet.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.loopr.wallet.common.AppGlobal;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

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

    }

    @OnClick(R2.id.wallet_confirm_next)
    public void onNextClick(ImageView imageView){

    }

    @OnTextChanged(value = R2.id.wallet_confirm, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onWalletNameAfterTextChanged(Editable s) {
        ButterKnife.apply(mWalletConfirmLine,STATUS, !TextUtils.isEmpty(s.toString()));
    }

}
