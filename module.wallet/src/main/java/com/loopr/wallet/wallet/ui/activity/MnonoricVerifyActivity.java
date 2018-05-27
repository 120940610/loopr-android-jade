package com.loopr.wallet.wallet.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.common.ui.widget.ViewFlow;
import com.loopr.wallet.common.utils.LogUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Conf;
import com.loopr.wallet.wallet.ui.adapter.ViewFlowAdapter;
import com.loopr.wallet.wallet.util.RandomUtil;

import java.util.HashSet;
import java.util.Random;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by snow on 2018/5/27.
 */
@Route(path = "/wallet/MnonoricVerifyActivity")
public class MnonoricVerifyActivity extends BaseActivity{

    private static final String TAG="MnonoricVerifyActivity";

    @BindView(R2.id.title_bar)
    public CommonTitleBar commonTitleBar;

    @BindDrawable(R2.drawable.wallet_rectangle_black_bg)
    Drawable mWalletBlackBg;

    @BindDrawable(R2.drawable.wallet_rectangle_white_bg)
    Drawable mWalletWhiteBg;

    @BindView(R2.id.view_flow)
    ViewFlow mViewFlow;

    SparseArray<String> mNPart=new SparseArray<>();
    ViewFlowAdapter mViewFlowAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_mn_verify_activity);
        ButterKnife.bind(this);
        initData();
        mViewFlowAdapter=new ViewFlowAdapter(mViewFlow,this);
        mViewFlow.setAdapter(mViewFlowAdapter);
        mViewFlowAdapter.setData(mNPart);
        mViewFlow.setOnViewSwitchListener(new ViewFlow.ViewSwitchListener() {
            @Override
            public void onSwitched(View view, int position) {
            }
        });

        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void initData(){
        Object[] random=RandomUtil.random24();
        for (int i=0;i<random.length;i++){
            int key=((Integer)random[i]).intValue();
            LogUtils.d("TAG","int: "+i);
            LogUtils.d("TAG","key: "+key);
            LogUtils.d("TAG","value: "+ Conf.mnemonic.get(key-1));
            mNPart.put(key, Conf.mnemonic.get(key-1));
        }
    }


}
