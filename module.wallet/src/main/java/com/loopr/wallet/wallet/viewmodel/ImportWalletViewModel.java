package com.loopr.wallet.wallet.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.loopr.wallet.wallet.entity.Wallet;
import com.loopr.wallet.wallet.interact.ImportWalletInteract;
import com.loopr.wallet.wallet.ui.event.OnImportWalletListener;


public class ImportWalletViewModel extends BaseViewModel implements OnImportWalletListener{

    private final ImportWalletInteract importWalletInteract;
    private final MutableLiveData<Wallet> wallet = new MutableLiveData<>();

    ImportWalletViewModel(ImportWalletInteract importWalletInteract) {
        this.importWalletInteract = importWalletInteract;
    }

    @Override
    public void onMnemonic(String mnemonic) {

    }

    @Override
    public void onKeystore(String keystore, String password) {
        progress.postValue(true);
        importWalletInteract
                .importKeystore(keystore, password)
                .subscribe(this::onWallet, this::onError);
    }

    @Override
    public void onPrivateKey(String key) {
        progress.postValue(true);
        importWalletInteract
                .importPrivateKey(key)
                .subscribe(this::onWallet, this::onError);
    }

    public LiveData<Wallet> wallet() {
        return wallet;
    }

    private void onWallet(Wallet wallet) {
        progress.postValue(false);
        this.wallet.postValue(wallet);
    }
}
