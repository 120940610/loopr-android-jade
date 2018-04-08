package com.loopr.wallet.wallet.di;

import android.content.Context;

import com.google.gson.Gson;
import com.loopr.wallet.wallet.repo.EthereumNetworkRepository;
import com.loopr.wallet.wallet.repo.EthereumNetworkRepositoryType;
import com.loopr.wallet.wallet.repo.PreferenceRepositoryType;
import com.loopr.wallet.wallet.repo.SharedPreferenceRepository;
import com.loopr.wallet.wallet.repo.WalletRepository;
import com.loopr.wallet.wallet.repo.WalletRepositoryType;
import com.loopr.wallet.wallet.service.AccountKeystoreService;
import com.loopr.wallet.wallet.service.TickerService;
import java.io.File;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class RepositoriesModule {
	@Singleton
	@Provides
	PreferenceRepositoryType providePreferenceRepository(Context context) {
		return new SharedPreferenceRepository(context);
	}

	@Singleton
	@Provides
	WalletRepositoryType provideWalletRepository(
			OkHttpClient okHttpClient,
			PreferenceRepositoryType preferenceRepositoryType,
			AccountKeystoreService accountKeystoreService,
			EthereumNetworkRepositoryType networkRepository) {
		return new WalletRepository(
				okHttpClient, preferenceRepositoryType, accountKeystoreService, networkRepository);
	}

	@Singleton
	@Provides
	AccountKeystoreService provideAccountKeyStoreService(Context context) {
        File file = new File(context.getFilesDir(), "keystore/keystore");
		//return new GethKeystoreAccountService(file);
		return null;
	}


	@Singleton
	@Provides
	EthereumNetworkRepositoryType provideEthereumNetworkRepository(
            PreferenceRepositoryType preferenceRepository,
            TickerService tickerService) {
		return new EthereumNetworkRepository(preferenceRepository, tickerService);
	}

}
