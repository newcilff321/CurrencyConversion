package org.homework.service;

import org.homework.data.CoinDeskMainRequestData;
import org.homework.data.FiatCurrencyResponseData;

import java.util.List;

public interface ExchangeRateService {
    List<FiatCurrencyResponseData> getFiatCurrencyRate();

    void updateExchangeRate(CoinDeskMainRequestData coinDesk);
}
