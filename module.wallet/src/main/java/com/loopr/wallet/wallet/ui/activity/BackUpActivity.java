package com.loopr.wallet.wallet.ui.activity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.common.utils.ScreenUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Conf;
import com.loopr.wallet.wallet.ui.adapter.MnenoricRecycleViewAdapter;
import com.loopr.wallet.wallet.util.ViewUtils;

import butterknife.BindBitmap;
import butterknife.BindDrawable;
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

    @BindView(R2.id.wallet_backup_next)
    public TextView mWalletBackupNext;

    @BindView(R2.id.wallet_backup_previous)
    public TextView mWalletBackupPrevious;

    @BindView(R2.id.grid_recycle)
    public RecyclerView mRecyclerView;

    @BindString(R2.string.wallet_verify_dialog_msg)
    String mWalletVerifyMsg;

    @BindString(R2.string.wallet_verify_dialog_cancel)
    String mWalletVerifyCancel;

    @BindString(R2.string.wallet_verify_dialog_confirm)
    String mWalletVerifyConfirm;

    @BindDrawable(R2.drawable.next_arror)
    Drawable mNextArror;

    @BindDrawable(R2.drawable.previous_arror)
    Drawable mBackArror;

    MnenoricRecycleViewAdapter mnenoricRecycleViewAdapter;

    SparseArray<String> mNPart1=new SparseArray<>();
    SparseArray<String> mNPart2=new SparseArray<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_backup_activity);
        ButterKnife.bind(this);
        initData();
        mNextArror.setBounds(8, 4, ScreenUtils.dip2px(this,12), ScreenUtils.dip2px(this,12));
        mBackArror.setBounds(12, 4, mBackArror.getIntrinsicWidth(), ScreenUtils.dip2px(this,12));

        mWalletBackupNext.setCompoundDrawables(null, null, mNextArror, null);
        mWalletBackupPrevious.setCompoundDrawables(mBackArror, null, null, null);
        mnenoricRecycleViewAdapter=new MnenoricRecycleViewAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(mnenoricRecycleViewAdapter);
        mWalletBackupNext.setVisibility(View.VISIBLE);
        mnenoricRecycleViewAdapter.setData(mNPart1);
        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void initData(){
        int count=0;
        for (String mn : Conf.mnemonic){
            if(count<12){
                mNPart1.put(count+1,mn);
            }else {
                mNPart2.put(count+1,mn);
            }
            count++;
        }
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

    @OnClick(R2.id.wallet_backup_next)
    public void onNextClick(TextView textView){
        textView.setVisibility(View.GONE);
        mnenoricRecycleViewAdapter.setData(mNPart2);
        mWalletBackupPrevious.setVisibility(View.VISIBLE);
        mWalletVerify.setVisibility(View.VISIBLE);

        //ARouter.getInstance().build("/wallet/BackUpActivity").navigation();
    }

    @OnClick(R2.id.wallet_backup_previous)
    public void onPreviousClick(TextView textView){
        textView.setVisibility(View.GONE);
        mnenoricRecycleViewAdapter.setData(mNPart1);
        mWalletBackupNext.setVisibility(View.VISIBLE);
        mWalletVerify.setVisibility(View.GONE);
    }

}
