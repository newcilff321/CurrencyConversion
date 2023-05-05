package org.homework.service;

import org.homework.data.FiatCurrencyUpdateMainRequestData;
import org.homework.model.FiatCurrency;

import java.util.List;

public interface FiatCurrencyService {
    List<FiatCurrency> getFiatCurrency();

    void createFiatCurrency(FiatCurrencyUpdateMainRequestData createInfo);

    void updateFiatCurrency(FiatCurrencyUpdateMainRequestData updateInfo);

    void deleteFiatCurrency(List<String> fiatCodes);
}
