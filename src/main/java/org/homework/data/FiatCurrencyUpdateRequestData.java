package org.homework.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiatCurrencyUpdateRequestData {

    private String code;

    private String symbol;

    private String description;

    private String zhName;
}
