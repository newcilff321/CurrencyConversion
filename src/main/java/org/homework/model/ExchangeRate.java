package org.homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "exchange_rate")
@ToString(exclude = {
        "exchange_rate",
        "fiat_currency",
        "crypto_currency"
})
public class ExchangeRate extends BaseModel {
    private Double rateFloat;

    private Date lastModifiedDate;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private CryptoCurrency cryptoCurrency;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private FiatCurrency fiatCurrency;
}
