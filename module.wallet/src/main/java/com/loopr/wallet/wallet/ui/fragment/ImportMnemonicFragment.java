package com.loopr.wallet.wallet.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.loopr.wallet.wallet.ui.event.OnImportWalletListener;

/**
 * Created by snow on 2018/5/3.
 */
public class ImportMnemonicFragment extends Fragment implements View.OnClickListener {

    public OnImportWalletListener onImportWalletListener=null;
    public static ImportMnemonicFragment create() {
        return new ImportMnemonicFragment();
    }


    @Override
    public void onClick(View view) {

    }

    public void setOnImportWalletListener(OnImportWalletListener onImportWalletListener) {
        this.onImportWalletListener = onImportWalletListener;
    }
}
