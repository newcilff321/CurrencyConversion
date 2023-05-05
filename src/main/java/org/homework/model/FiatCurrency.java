package org.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
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
@Table(name = "fiat_currency")
@ToString(exclude = {
        "fiat_currency",
        "exchange_rate"
})
public class FiatCurrency extends BaseModel {
    private String code;

    private String symbol;

    private String description;

    private String zhName;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "fiatCurrency",orphanRemoval = true
    )
    @JsonIgnore
    private Set<ExchangeRate> exchangeRateSet;

    public void addExchangeRateSet(ExchangeRate exchangeRate) {
        exchangeRateSet.add(exchangeRate);
        exchangeRate.setFiatCurrency(this);
    }
}
