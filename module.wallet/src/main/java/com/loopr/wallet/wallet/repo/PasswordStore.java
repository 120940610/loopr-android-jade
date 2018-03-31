package com.loopr.wallet.wallet.repo;


import com.loopr.wallet.wallet.entity.Wallet;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PasswordStore {
	Single<String> getPassword(Wallet wallet);
	Completable setPassword(Wallet wallet, String password);
	Single<String> generatePassword();
}
