package com.loopr.wallet.wallet.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.loopr.wallet.common.AppGlobal;
import com.loopr.wallet.common.utils.ToastUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.ui.event.OnImportWalletListener;
import com.loopr.wallet.wallet.util.KeyUtil;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ImportKeystoreFragment extends Fragment{

    public OnImportWalletListener onImportWalletListener=null;
    public static ImportKeystoreFragment create() {
        return new ImportKeystoreFragment();
    }

    public View mRootViw;

    @BindView(R2.id.wallet_import_keystore_text)
    EditText mWalletImportKeystoreText;

    @BindView(R2.id.wallet_import_keystore_pwd)
    EditText mWalletImportKeystorePwd;

    @BindView(R2.id.import_wallet_btn)
    TextView mWalletImportBtn;

    @BindString(R2.string.wallet_import_keystore)
    String mInputKeystore;

    @BindString(R2.string.wallet_import_pwd)
    String mInputPwd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootViw= LayoutInflater.from(getContext())
                .inflate(R.layout.wallet_import_keystore_fragment, container, false);
        ButterKnife.bind( this , mRootViw ) ;
        return mRootViw;
    }

    @OnClick(R2.id.import_wallet_btn)
    public void onImportClick(TextView textView){
        String keystore = mWalletImportKeystoreText.getText().toString();
        String password = mWalletImportKeystorePwd.getText().toString();
        if(TextUtils.isEmpty(keystore)){
            ToastUtils.showLongToast(AppGlobal.getContext(),mInputKeystore);
            return;
        }
        if(TextUtils.isEmpty(password)){
            ToastUtils.showLongToast(AppGlobal.getContext(),mInputPwd);
            return;
        }
        onImportWalletListener.onKeystore(keystore, password);
    }

    public void setOnImportWalletListener(OnImportWalletListener onImportWalletListener) {
        this.onImportWalletListener = onImportWalletListener;
    }
}
