package org.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.data.CoinDeskMainRequestData;
import org.homework.data.FiatCurrencyResponseData;
import org.homework.service.ExchangeRateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/coinDesk")
@RestController
public class CoinDeskController {
    private final ExchangeRateService exchangeRateService;

    @PostMapping("/getFiatCurrencyRate")
    public List<FiatCurrencyResponseData> getFiatCurrencyRate() {

        return exchangeRateService.getFiatCurrencyRate();
    }

    @PostMapping("/updateFiatCurrencyRate")
    public void updateFiatCurrencyRate(@RequestBody CoinDeskMainRequestData coinDesk) {

        exchangeRateService.updateExchangeRate(coinDesk);
    }

}
