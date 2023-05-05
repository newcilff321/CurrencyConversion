package org.homework.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public interface FiatCurrencyResponseData {
    String getFiatCode();

    String getCryptoCode();

    double getRate();

    String getFiatZhName();
}
