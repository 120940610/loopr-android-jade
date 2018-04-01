package com.loopr.wallet.wallet.di;


import com.loopr.wallet.wallet.interact.CreateWalletInteract;
import com.loopr.wallet.wallet.interact.FetchWalletsInteract;
import com.loopr.wallet.wallet.interact.FindDefaultWalletInteract;
import com.loopr.wallet.wallet.interact.SetDefaultWalletInteract;
import com.loopr.wallet.wallet.repo.PasswordStore;
import com.loopr.wallet.wallet.repo.WalletRepositoryType;
import com.loopr.wallet.wallet.viewmodel.WalletsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class AccountsManageModule {

	@Provides
	WalletsViewModelFactory provideAccountsManageViewModelFactory(
			CreateWalletInteract createWalletInteract,
			SetDefaultWalletInteract setDefaultWalletInteract,
			FetchWalletsInteract fetchWalletsInteract,
			FindDefaultWalletInteract findDefaultWalletInteract) {
		return new WalletsViewModelFactory(createWalletInteract,
                setDefaultWalletInteract,
                fetchWalletsInteract,
                findDefaultWalletInteract);
	}

	@Provides
    CreateWalletInteract provideCreateAccountInteract(
			WalletRepositoryType accountRepository, PasswordStore passwordStore) {
		return new CreateWalletInteract(accountRepository, passwordStore);
	}

	@Provides
    SetDefaultWalletInteract provideSetDefaultAccountInteract(WalletRepositoryType accountRepository) {
		return new SetDefaultWalletInteract(accountRepository);
	}

	@Provides
    FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
		return new FetchWalletsInteract(accountRepository);
	}

	@Provides
    FindDefaultWalletInteract provideFindDefaultAccountInteract(WalletRepositoryType accountRepository) {
		return new FindDefaultWalletInteract(accountRepository);
	}
}
