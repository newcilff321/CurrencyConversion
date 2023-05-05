package org.homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "crypto_currency")
@ToString(exclude = {
        "exchange_rate",
        "crypto_currency"
})
public class CryptoCurrency extends BaseModel{
    private String code;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "cryptoCurrency"
    )
    private Set<ExchangeRate> exchangeRateSet;

    public void addExchangeRateSet(ExchangeRate exchangeRate) {
        exchangeRateSet.add(exchangeRate);
        exchangeRate.setCryptoCurrency(this);
    }
}
