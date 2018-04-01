package com.loopr.wallet.wallet.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.loopr.wallet.wallet.interact.CreateWalletInteract;
import com.loopr.wallet.wallet.interact.FetchWalletsInteract;
import com.loopr.wallet.wallet.interact.FindDefaultWalletInteract;
import com.loopr.wallet.wallet.interact.SetDefaultWalletInteract;

import javax.inject.Inject;

public class WalletsViewModelFactory implements ViewModelProvider.Factory {

	private final CreateWalletInteract createWalletInteract;
	private final SetDefaultWalletInteract setDefaultWalletInteract;
	private final FetchWalletsInteract fetchWalletsInteract;
	private final FindDefaultWalletInteract findDefaultWalletInteract;


    @Inject
	public WalletsViewModelFactory(
            CreateWalletInteract createWalletInteract,
            SetDefaultWalletInteract setDefaultWalletInteract,
            FetchWalletsInteract fetchWalletsInteract,
            FindDefaultWalletInteract findDefaultWalletInteract) {
		this.createWalletInteract = createWalletInteract;
		this.setDefaultWalletInteract = setDefaultWalletInteract;
		this.fetchWalletsInteract = fetchWalletsInteract;
		this.findDefaultWalletInteract = findDefaultWalletInteract;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		return (T) new WalletsViewModel(
                createWalletInteract,
                setDefaultWalletInteract,
                fetchWalletsInteract,
                findDefaultWalletInteract);
	}
}
