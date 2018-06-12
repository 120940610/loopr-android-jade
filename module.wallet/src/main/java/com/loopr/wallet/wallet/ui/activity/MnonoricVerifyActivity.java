package com.loopr.wallet.wallet.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.loopr.wallet.common.ui.activity.BaseActivity;
import com.loopr.wallet.common.ui.widget.CommonTitleBar;
import com.loopr.wallet.common.utils.LogUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Conf;
import com.loopr.wallet.wallet.util.RandomUtil;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_mn_verify_activity);
        ButterKnife.bind(this);
        initData();

        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void initData(){
        Conf.mNPart=new SparseArray<>();
        Conf.random=RandomUtil.random24();

        int key=((Integer)Conf.random[0]).intValue();
        LogUtils.d("TAG","int: "+0);
        LogUtils.d("TAG","key: "+key);
        LogUtils.d("TAG","value: "+ Conf.mnemonic.get(key-1));
        Conf.mNPart.put(key, Conf.mnemonic.get(key-1));
    }


}
