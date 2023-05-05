package org.homework.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.data.FiatCurrencyResponseData;
import org.homework.data.FiatCurrencyUpdateMainRequestData;
import org.homework.data.FiatCurrencyUpdateRequestData;
import org.homework.model.FiatCurrency;
import org.homework.repository.FiatCurrencyDao;
import org.homework.service.FiatCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FiatCurrencyServiceImpl implements FiatCurrencyService {
    private final FiatCurrencyDao fiatCurrencyDao;

    private final ObjectMapper objectMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FiatCurrency> getFiatCurrency() {
        return fiatCurrencyDao.findAll();
    }

    @Override
    @Transactional
    public void createFiatCurrency(FiatCurrencyUpdateMainRequestData createInfo) {
        List<FiatCurrency> fiatCurrencyList = new ArrayList<>();

        for (FiatCurrencyUpdateRequestData fiat : createInfo.getData()) {
            FiatCurrency fiatCurrency = FiatCurrency.builder()
                    .code(fiat.getCode())
                    .description(fiat.getDescription())
                    .symbol(fiat.getSymbol())
                    .zhName(fiat.getZhName())
                    .build();

            fiatCurrencyList.add(fiatCurrency);
        }

        fiatCurrencyDao.saveAll(fiatCurrencyList);
    }

    @Override
    @Transactional
    public void updateFiatCurrency(FiatCurrencyUpdateMainRequestData updateInfo) {
        List<FiatCurrency> updateFiatCurrencyList = new ArrayList<>();
        List<String> fiatCodes = updateInfo.getData().stream().map(FiatCurrencyUpdateRequestData::getCode).collect(Collectors.toList());

        // key: fiatCurrency code, value: FiatCurrency
        Map<String, FiatCurrency> fiatCurrencyMap = fiatCurrencyDao.getFiatCurrencyByCodes(fiatCodes).stream().collect(Collectors.toMap(FiatCurrency::getCode, Function.identity()));

        for (FiatCurrencyUpdateRequestData fiat : updateInfo.getData()) {
            FiatCurrency fiatCurrency = fiatCurrencyMap.get(fiat.getCode());

            if (fiatCurrency != null) {
                fiatCurrency.setCode(fiat.getCode());
                fiatCurrency.setDescription(fiat.getDescription());
                fiatCurrency.setSymbol(fiat.getSymbol());
                fiatCurrency.setZhName(fiat.getZhName());

                updateFiatCurrencyList.add(fiatCurrency);
            }
        }

        fiatCurrencyDao.saveAll(updateFiatCurrencyList);
    }

    @Override
    @Transactional
    public void deleteFiatCurrency(List<String> fiatCodes) {
        List<FiatCurrency> fiatCurrencyList = fiatCurrencyDao.getFiatCurrencyByCodes(fiatCodes);

        fiatCurrencyDao.deleteAll(fiatCurrencyList);
    }
}
