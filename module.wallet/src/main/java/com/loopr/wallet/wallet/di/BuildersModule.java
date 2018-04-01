package com.loopr.wallet.wallet.di;


import com.loopr.wallet.wallet.ui.activity.PasswdConfirmActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = AccountsManageModule.class)
	abstract PasswdConfirmActivity bindManageWalletsModule();

}
