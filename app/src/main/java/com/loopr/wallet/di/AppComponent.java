package com.loopr.wallet.di;

import com.loopr.wallet.MainApplication;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import com.loopr.wallet.wallet.di.ToolsModule;
@Singleton
@Component(modules = {
		AndroidSupportInjectionModule.class,
		ToolsModule.class,})
public interface AppComponent {

	@Component.Builder
	interface Builder {
		@BindsInstance
		Builder application(MainApplication app);
		AppComponent build();
	}
	void inject(MainApplication app);
}
