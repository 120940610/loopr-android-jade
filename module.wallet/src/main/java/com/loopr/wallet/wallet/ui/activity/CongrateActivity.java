package com.loopr.wallet.wallet.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Conf;

import java.security.SecureRandom;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;

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
        StringBuilder sb = new StringBuilder();
        byte[] entropy = new byte[Words.TWENTY_FOUR.byteLength()];
        new SecureRandom().nextBytes(entropy);
        new MnemonicGenerator(English.INSTANCE)
                .createMnemonic(entropy, sb::append);
        String mn=sb.toString();
        Conf.mnemonic=Arrays.asList(mn.split(" "));
        System.out.println("MnemonicGenerator ："+Conf.mnemonic.get(3));
        ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
        finish();
    }

}
