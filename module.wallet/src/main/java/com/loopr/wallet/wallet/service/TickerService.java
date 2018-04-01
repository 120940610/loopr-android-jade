package com.loopr.wallet.wallet.service;


import com.loopr.wallet.wallet.entity.Ticker;

import io.reactivex.Observable;

public interface TickerService {

    Observable<Ticker> fetchTickerPrice(String ticker);
}
