package com.loopr.wallet.wallet.di;

import android.app.Application;
import android.content.Context;
import com.loopr.wallet.wallet.repo.PasswordStore;
import com.loopr.wallet.wallet.repo.WalletPasswordStore;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ToolsModule {
	@Provides
	Context provideContext(Application application) {
		return application.getApplicationContext();
	}


	@Singleton
	@Provides
	PasswordStore passwordStore(Context context) {
		return new WalletPasswordStore(context);
	}
}
