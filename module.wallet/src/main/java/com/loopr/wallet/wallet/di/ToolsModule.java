package com.loopr.wallet.wallet.di;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.loopr.wallet.wallet.repo.PasswordStore;
import com.loopr.wallet.wallet.repo.WalletPasswordStore;
import com.loopr.wallet.wallet.util.LogInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ToolsModule {
	@Provides
	Context provideContext(Application application) {
		return application.getApplicationContext();
	}


	@Singleton
	@Provides
	Gson provideGson() {
		return new Gson();
	}

	@Singleton
	@Provides
	OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder()
				.addInterceptor(new LogInterceptor())
				.build();
	}


	@Singleton
	@Provides
	PasswordStore passwordStore(Context context) {
		return new WalletPasswordStore(context);
	}

}
