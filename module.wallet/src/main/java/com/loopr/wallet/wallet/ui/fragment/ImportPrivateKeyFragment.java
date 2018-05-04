package com.loopr.wallet.wallet.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.loopr.wallet.wallet.ui.event.OnImportWalletListener;

public class ImportPrivateKeyFragment extends Fragment implements View.OnClickListener {

    public OnImportWalletListener onImportWalletListener=null;

    public static ImportPrivateKeyFragment create() {
        return new ImportPrivateKeyFragment();
    }

    @Override
    public void onClick(View view) {

    }

    public void setOnImportWalletListener(OnImportWalletListener onImportWalletListener) {
        this.onImportWalletListener = onImportWalletListener;
    }
}
