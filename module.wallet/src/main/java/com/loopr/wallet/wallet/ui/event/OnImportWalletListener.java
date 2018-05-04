package com.loopr.wallet.wallet.ui.event;

public interface OnImportWalletListener {
    void onMnemonic(String mnemonic);
    void onKeystore(String keystore, String password);
    void onPrivateKey(String key);
}
