package com.loopr.wallet.wallet.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
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
 * Created by snow on 2018/3/17.
 */
@Route(path = "/wallet/CreateWalletActivity")
public class CreateWalletActivity extends BaseActivity{
    @BindView(R2.id.title_bar)
    public CommonTitleBar commonTitleBar;

    @BindView(R2.id.wallet_name)
    public EditText mWalletName;

    @BindView(R2.id.wallet_name_line)
    public View mWalletNameLine;

    @BindView(R2.id.wallet_secret)
    public EditText mWalletSecret;

    @BindView(R2.id.wallet_secret_line)
    public View mWalletSecretLine;

    @BindView(R2.id.wallet_next_img)
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
        setContentView(R.layout.wallet_create_activity);
        ButterKnife.bind(this);

        ButterKnife.apply(mWalletNameLine,STATUS, !TextUtils.isEmpty(mWalletName.getText()));
        ButterKnife.apply(mWalletSecretLine,STATUS,!TextUtils.isEmpty(mWalletSecret.getText()));

        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @OnClick(R2.id.wallet_next_img)
    public void onNextClick(ImageView imageView){
        ARouter.getInstance().build("/wallet/PasswdConfirmActivity").navigation();

    }

    @OnTextChanged(value = R2.id.wallet_name, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onWalletNameAfterTextChanged(Editable s) {
        ButterKnife.apply(mWalletNameLine,STATUS, !TextUtils.isEmpty(s.toString()));
    }

    @OnTextChanged(value =R2.id.wallet_secret, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onWalletSecretAfterTextChanged(Editable s) {
        ButterKnife.apply(mWalletSecretLine,STATUS,!TextUtils.isEmpty(s.toString()));

    }
}
