package com.loopr.wallet.wallet.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.ui.event.OnImportWalletListener;


public class ImportKeystoreFragment extends Fragment implements View.OnClickListener {

    public OnImportWalletListener onImportWalletListener=null;
    public static ImportKeystoreFragment create() {
        return new ImportKeystoreFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext())
                .inflate(R.layout.wallet_import_keystore_fragment, container, false);
    }

    @Override
    public void onClick(View view) {

    }

    public void setOnImportWalletListener(OnImportWalletListener onImportWalletListener) {
        this.onImportWalletListener = onImportWalletListener;
    }
}
