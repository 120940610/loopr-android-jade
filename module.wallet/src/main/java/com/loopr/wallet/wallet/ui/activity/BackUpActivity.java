package com.loopr.wallet.wallet.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.util.ViewUtils;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by snow on 2018/4/19.
 */
@Route(path = "/wallet/BackUpActivity")
public class BackUpActivity extends BaseActivity{
    @BindView(R2.id.title_bar)
    public CommonTitleBar commonTitleBar;

    @BindView(R2.id.wallet_verify)
    public TextView mWalletVerify;

    @BindView(R2.id.wallet_backup_move)
    public TextView mWalletBackupMove;

    @BindView(R2.id.grid_recycle)
    public RecyclerView mRecyclerView;

    @BindString(R2.string.wallet_verify_dialog_msg)
    String mWalletVerifyMsg;

    @BindString(R2.string.wallet_verify_dialog_cancel)
    String mWalletVerifyCancel;
    @BindString(R2.string.wallet_verify_dialog_confirm)
    String mWalletVerifyConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_backup_activity);
        ButterKnife.bind(this);

        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick(R2.id.wallet_verify)
    public void onVerifyClick(TextView textView){
        //ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
        ViewUtils.showCommonDialog(this, mWalletVerifyMsg,mWalletVerifyCancel,mWalletVerifyConfirm,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {

            }
        },new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    @OnClick(R2.id.wallet_backup_move)
    public void onMoveClick(TextView textView){
        //ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
    }

}
