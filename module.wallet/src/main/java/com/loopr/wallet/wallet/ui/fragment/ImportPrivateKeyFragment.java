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
import android.widget.TextView;

import com.loopr.wallet.common.AppGlobal;
import com.loopr.wallet.common.utils.ToastUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.ui.event.OnImportWalletListener;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImportPrivateKeyFragment extends Fragment{

    public OnImportWalletListener onImportWalletListener=null;

    public View mRootViw;

    @BindView(R2.id.wallet_import_private_text)
    EditText mWalletImportPrivateText;

    @BindView(R2.id.import_wallet_btn)
    TextView mWalletImportBtn;

    @BindString(R2.string.wallet_import_private)
    String mInputPrivate;

    public static ImportPrivateKeyFragment create() {
        return new ImportPrivateKeyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootViw= LayoutInflater.from(getContext())
                .inflate(R.layout.wallet_import_private_fragment, container, false);
        ButterKnife.bind( this , mRootViw ) ;
        return mRootViw;
    }

    @OnClick(R2.id.import_wallet_btn)
    public void onImportClick(TextView textView){
        String mPrivate = mWalletImportPrivateText.getText().toString();
        if(TextUtils.isEmpty(mPrivate)){
            ToastUtils.showLongToast(AppGlobal.getContext(),mInputPrivate);
            return;
        }
        onImportWalletListener.onPrivateKey(mPrivate);
    }

    public void setOnImportWalletListener(OnImportWalletListener onImportWalletListener) {
        this.onImportWalletListener = onImportWalletListener;
    }
}
