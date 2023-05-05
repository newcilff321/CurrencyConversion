package service.impl;

import org.homework.Main;
import org.homework.data.FiatCurrencyUpdateMainRequestData;
import org.homework.data.FiatCurrencyUpdateRequestData;
import org.homework.model.FiatCurrency;
import org.homework.repository.FiatCurrencyDao;
import org.homework.service.impl.FiatCurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = Main.class)
@WebAppConfiguration("test/resources")
public class FiatCurrencyServiceImplTest {
    @InjectMocks
    FiatCurrencyServiceImpl fiatCurrencyServiceImpl;

    @Mock
    FiatCurrencyDao fiatCurrencyDao;

    @BeforeEach
    void setup() {
        FiatCurrency fiatCurrency = FiatCurrency.builder()
                .code("JPY")
                .symbol("&JPY;")
                .zhName("日元")
                .description("JPY")
                .build();

        fiatCurrencyDao.save(fiatCurrency);
    }

    @Test
    void getFiatCurrency() {
        List<FiatCurrency> verify = fiatCurrencyDao.findAll();

        Mockito.when(fiatCurrencyServiceImpl.getFiatCurrency()).thenReturn(verify);
    }

    @Test
    void createFiatCurrency() {
        FiatCurrencyUpdateRequestData fiatCurrencyUpdateRequestData = FiatCurrencyUpdateRequestData.builder()
                .code("USD")
                .description("United States Dollar")
                .symbol("&#36;")
                .zhName("美元")
                .build();

        List<FiatCurrencyUpdateRequestData> data = new ArrayList<>();
        data.add(fiatCurrencyUpdateRequestData);

        FiatCurrencyUpdateMainRequestData fiatCurrencyUpdateMainRequestData = FiatCurrencyUpdateMainRequestData.builder()
                .data(data)
                .build();

        // save
        fiatCurrencyServiceImpl.createFiatCurrency(fiatCurrencyUpdateMainRequestData);

        List<FiatCurrency> verify = fiatCurrencyDao.getFiatCurrencyByCodes(List.of("USD"));
        List<FiatCurrency> verifyAll = fiatCurrencyDao.findAll();

        Mockito.when(verifyAll).thenReturn(verify);
    }

    @Test
    void updateFiatCurrency() {
        FiatCurrencyUpdateRequestData fiatCurrencyUpdateRequestData = FiatCurrencyUpdateRequestData.builder()
                .code("JPY")
                .description("JPY")
                .symbol("&ABC;")
                .zhName("日元")
                .build();

        List<FiatCurrencyUpdateRequestData> data = new ArrayList<>();
        data.add(fiatCurrencyUpdateRequestData);

        FiatCurrencyUpdateMainRequestData fiatCurrencyUpdateMainRequestData = FiatCurrencyUpdateMainRequestData.builder()
                .data(data)
                .build();

        // save
        fiatCurrencyServiceImpl.updateFiatCurrency(fiatCurrencyUpdateMainRequestData);

        List<FiatCurrency> verify = fiatCurrencyDao.getFiatCurrencyByCodes(List.of("JPY"));
        List<FiatCurrency> verifyAll = fiatCurrencyDao.findAll();

        Mockito.when(verifyAll.get(0).getSymbol()).thenReturn(verify.get(0).getSymbol());
    }

    @Test
    void deleteFiatCurrency() {
        List<String> deleteCodeList = List.of("JPY");

        fiatCurrencyServiceImpl.deleteFiatCurrency(deleteCodeList);

        List<FiatCurrency> verifyAll = fiatCurrencyDao.findAll();

        Mockito.when(verifyAll).thenReturn(null);
    }
}
