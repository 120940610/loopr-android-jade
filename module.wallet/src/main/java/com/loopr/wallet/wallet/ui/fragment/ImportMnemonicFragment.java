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

/**
 * Created by snow on 2018/5/3.
 */
public class ImportMnemonicFragment extends Fragment {

    public OnImportWalletListener onImportWalletListener=null;
    public static ImportMnemonicFragment create() {
        return new ImportMnemonicFragment();
    }

    public View mRootViw;

    @BindView(R2.id.wallet_import_nmonoric_text)
    EditText mWalletImportNmonoricText;

    @BindView(R2.id.import_wallet_btn)
    TextView mWalletImportBtn;

    @BindString(R2.string.wallet_import_mnemonic)
    String mInputMnemonic;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootViw= LayoutInflater.from(getContext())
                .inflate(R.layout.wallet_import_nmonoric_fragment, container, false);
        ButterKnife.bind( this , mRootViw ) ;
        return mRootViw;
    }

    @OnClick(R2.id.import_wallet_btn)
    public void onImportClick(TextView textView){
        String mMnemonic = mWalletImportNmonoricText.getText().toString();
        if(TextUtils.isEmpty(mMnemonic)){
            ToastUtils.showLongToast(AppGlobal.getContext(),mInputMnemonic);
            return;
        }
        onImportWalletListener.onMnemonic(mMnemonic);
    }

    public void setOnImportWalletListener(OnImportWalletListener onImportWalletListener) {
        this.onImportWalletListener = onImportWalletListener;
    }
}
