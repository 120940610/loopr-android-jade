package com.loopr.wallet.wallet.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.wallet.R;

/**
 * Created by snow on 2018/3/19.
 */

@Route(path = "/wallet/ManageWalletActivity")
public class ManageWalletActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mCreateWalletTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_wallet_activity);
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
