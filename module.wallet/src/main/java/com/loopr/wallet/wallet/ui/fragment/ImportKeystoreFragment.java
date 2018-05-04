package com.loopr.wallet.wallet.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.loopr.wallet.wallet.ui.event.OnImportWalletListener;


public class ImportKeystoreFragment extends Fragment implements View.OnClickListener {

    public OnImportWalletListener onImportWalletListener=null;
    public static ImportKeystoreFragment create() {
        return new ImportKeystoreFragment();
    }

    @Override
    public void onClick(View view) {

    }

    public void setOnImportWalletListener(OnImportWalletListener onImportWalletListener) {
        this.onImportWalletListener = onImportWalletListener;
    }
}
