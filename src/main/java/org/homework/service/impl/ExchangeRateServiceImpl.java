package org.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.data.CoinDeskMainRequestData;
import org.homework.data.FiatCurrencyResponseData;
import org.homework.model.CryptoCurrency;
import org.homework.model.ExchangeRate;
import org.homework.model.FiatCurrency;
import org.homework.repository.CryptoCurrencyDao;
import org.homework.repository.ExchangeRateDao;
import org.homework.repository.FiatCurrencyDao;
import org.homework.service.ExchangeRateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final FiatCurrencyDao fiatCurrencyDao;

    private final CryptoCurrencyDao cryptoCurrencyDao;

    private final ExchangeRateDao exchangeRateDao;

    // Query all fiat currency exchange rate
    public List<FiatCurrencyResponseData> getFiatCurrencyRate() {
        return fiatCurrencyDao.getFiatCurrencyRate();
    }

    @Override
    @Transactional
    public void updateExchangeRate(CoinDeskMainRequestData coinDesk) {
        List<ExchangeRate> resultExchangeRateList = new ArrayList<>();
        CryptoCurrency cryptoCurrency = cryptoCurrencyDao.getCryptoCurrencyByCode(coinDesk.getChartName()).orElse(null);
        Date updateTime = coinDesk.getTime();

        Map<String, FiatCurrency> fiatCurrencyMap = fiatCurrencyDao.findAll().stream().collect(Collectors.toMap(FiatCurrency::getCode, Function.identity()));
        Map<String, ExchangeRate> existRateMap = new HashMap<>();
        exchangeRateDao.getExistRate().forEach(er -> existRateMap.put(er.getFiatCurrency().getCode(), er));

        coinDesk.getBpi().forEach((m, n) -> {
            // new
            if (existRateMap.get(m) == null) {
                ExchangeRate exchangeRate = ExchangeRate.builder()
                        .rateFloat(n.getRateFloat())
                        .cryptoCurrency(cryptoCurrency)
                        .fiatCurrency(fiatCurrencyMap.get(n.getCode()))
                        .lastModifiedDate(updateTime)
                        .build();

                resultExchangeRateList.add(exchangeRate);
            } else {
                ExchangeRate exchangeRate = existRateMap.get(m);
                exchangeRate.setRateFloat(n.getRateFloat());
                exchangeRate.setLastModifiedDate(updateTime);

                resultExchangeRateList.add(exchangeRate);
            }
        });

        exchangeRateDao.saveAll(resultExchangeRateList);
    }
}
