package org.homework.repository;

import org.homework.data.FiatCurrencyResponseData;
import org.homework.model.FiatCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface FiatCurrencyDao extends JpaRepository<FiatCurrency, Long> {
    @Query(value = "SELECT fc.code AS fiatCode, fc.zh_name AS fiatZhName, er.rate_float AS rate, cc.code AS cryptoCode " +
            "FROM hw.fiat_currency fc " +
            "INNER JOIN hw.exchange_rate er ON er.fiat_currency_id = fc.id " +
            "INNER JOIN hw.crypto_currency cc ON cc.id = er.crypto_currency_id "
            , nativeQuery = true)
    List<FiatCurrencyResponseData> getFiatCurrencyRate();

    @Query(value = "SELECT fc.* FROM hw.fiat_currency fc " +
            "WHERE fc.code IN (?1)"
            , nativeQuery = true)
    List<FiatCurrency> getFiatCurrencyByCodes(List<String> codes);
}
