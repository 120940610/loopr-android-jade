package com.loopr.wallet.wallet.rx;

import com.loopr.wallet.wallet.entity.Wallet;
import com.loopr.wallet.wallet.repo.PasswordStore;
import com.loopr.wallet.wallet.repo.WalletRepositoryType;
import io.reactivex.CompletableOperator;
import io.reactivex.SingleTransformer;

public class Operators {

    public static SingleTransformer<Wallet, Wallet> savePassword(
            PasswordStore passwordStore, WalletRepositoryType walletRepository, String password) {
        return new SavePasswordOperator(passwordStore, walletRepository, password);
    }

    public static CompletableOperator completableErrorProxy(Throwable throwable) {
        return new CompletableErrorProxyOperator(throwable);
    }
}
