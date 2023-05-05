package service.impl;

import org.homework.Main;
import org.homework.model.FiatCurrency;
import org.homework.repository.CryptoCurrencyDao;
import org.homework.repository.ExchangeRateDao;
import org.homework.repository.FiatCurrencyDao;
import org.homework.service.FiatCurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
@WebAppConfiguration("test/resources")
public class FiatCurrencyServiceImplTest {
    @MockBean
    FiatCurrencyService fiatCurrencyService;

    @Autowired
    FiatCurrencyDao fiatCurrencyDao;

    @Autowired
    CryptoCurrencyDao cryptoCurrencyDao;
    @Autowired
    ExchangeRateDao exchangeRateDao;

    @BeforeEach
    void setup() {
        FiatCurrency fiatCurrency = FiatCurrency.builder()
                .code("USD")
                .symbol("&#36;")
                .zhName("美元")
                .description("United States Dollar")
                .build();

        fiatCurrencyDao.save(fiatCurrency);
    }

    @Test
    void getFiatCurrency() {
        List<FiatCurrency> result = fiatCurrencyService.getFiatCurrency();
        String resultCode = result.get(0).getCode();
        List<FiatCurrency> verify = fiatCurrencyDao.findAll();
        String verifyCode = verify.get(0).getCode();

        assertEquals(resultCode, verifyCode);
    }
}
