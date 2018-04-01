package com.loopr.wallet.wallet.repo;


import com.loopr.wallet.wallet.entity.NetworkInfo;
import com.loopr.wallet.wallet.entity.OnNetworkChangeListener;
import com.loopr.wallet.wallet.entity.Ticker;

import io.reactivex.Single;

public interface EthereumNetworkRepositoryType {

	NetworkInfo getDefaultNetwork();

	void setDefaultNetworkInfo(NetworkInfo networkInfo);

	NetworkInfo[] getAvailableNetworkList();

	void addOnChangeDefaultNetwork(OnNetworkChangeListener onNetworkChanged);

	Single<Ticker> getTicker();
}
