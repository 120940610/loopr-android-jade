package com.loopr.wallet.wallet.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.loopr.wallet.common.utils.LogUtils;
import com.loopr.wallet.common.utils.ScreenUtils;
import com.loopr.wallet.common.utils.ToastUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Conf;
import com.loopr.wallet.wallet.ui.adapter.MnenoricRecycleViewAdapter;
import com.loopr.wallet.wallet.ui.adapter.MnenoricVerifyChoseAdapter;
import com.loopr.wallet.wallet.ui.adapter.MnenoricVerifyListAdapter;
import com.loopr.wallet.wallet.ui.event.OnClickMnenoricListener;
import com.loopr.wallet.wallet.ui.manager.FlowItemDecoration;
import com.loopr.wallet.wallet.ui.manager.FlowLayoutManager;
import com.loopr.wallet.wallet.ui.manager.GridItemDecoration;
import com.loopr.wallet.wallet.util.RandomUtil;

import java.util.ArrayList;

import butterknife.BindDrawable;
import butterknife.BindString;
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

    @BindView(R2.id.wallet_mn_verify_chosed)
    RecyclerView mWalletVerifyChosed;

    @BindView(R2.id.wallet_mn_verify_list)
    RecyclerView mWalletVerifyList;

    @BindView(R2.id.wallet_verify_btn)
    TextView mWalletVerifyBtn;


    MnenoricVerifyChoseAdapter mnenoricVerifyChoseAdapter;
    MnenoricVerifyListAdapter mnenoricVerifyListAdapter;

    @BindString(R2.string.wallet_mn_verify_success)
    String mVerifySuccess;

    @BindString(R2.string.wallet_mn_verify_fail)
    String mVerifyfail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_mn_verify_activity);
        ButterKnife.bind(this);
        initData();

        mnenoricVerifyChoseAdapter=new MnenoricVerifyChoseAdapter(this);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        mWalletVerifyChosed.addItemDecoration(new FlowItemDecoration(ScreenUtils.dip2px(this,4)));
        mWalletVerifyChosed.setLayoutManager(flowLayoutManager);
        mWalletVerifyChosed.setAdapter(mnenoricVerifyChoseAdapter);
        mnenoricVerifyChoseAdapter.setData(Conf.mNChose);

        mnenoricVerifyListAdapter=new MnenoricVerifyListAdapter(this);
        mWalletVerifyList.setLayoutManager(new GridLayoutManager(this,3));
        mWalletVerifyList.addItemDecoration(new GridItemDecoration(ScreenUtils.dip2px(this,10)));
        mWalletVerifyList.setAdapter(mnenoricVerifyListAdapter);
        mnenoricVerifyListAdapter.setData(Conf.mNRandom);

        mnenoricVerifyListAdapter.setOnClickMnenoricListener(new OnClickMnenoricListener() {
            @Override
            public void onMnemonicClick(String mnemonic) {
                Conf.mNChose.add(mnemonic);
                mnenoricVerifyChoseAdapter.setData(Conf.mNChose);
            }
        });
        commonTitleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @OnClick(R2.id.wallet_verify_btn)
    public void onNextClick(TextView textView){
        if(verifyMnenoric()){
            ToastUtils.showShortToast(this,mVerifySuccess);
            ARouter.getInstance().build("/app/MainActivity").navigation();
        }else {
            ToastUtils.showShortToast(this,mVerifyfail);
            finish();
        }

    }

    private boolean verifyMnenoric(){
        if(Conf.mnemonic.size()!=24 || Conf.mNChose.size()!=24){
            return false;
        }
        for(int i=0;i<Conf.mnemonic.size();i++){
            String srcMnemonic=Conf.mnemonic.get(i);
            String choseMnemonic=Conf.mNChose.get(i);
            if(!srcMnemonic.equals(choseMnemonic)){
                return false;
            }
        }
        return true;
    }

    private void initData(){
        Conf.mNChose=new ArrayList<>();
        Conf.mNRandom=new ArrayList<>();
        Object[] random24=RandomUtil.random24();
        for(int i=0;i<random24.length;i++){
            int key=((Integer)random24[i]).intValue();
            LogUtils.d("TAG","int: "+i);
            LogUtils.d("TAG","key: "+key);
            LogUtils.d("TAG","value: "+ Conf.mnemonic.get(key-1));
            Conf.mNRandom.add(Conf.mnemonic.get(key-1));
        }
    }

    @Override
    protected void onDestroy() {
        Conf.mNRandom=null;
        Conf.mNChose=null;
        super.onDestroy();
    }
}
