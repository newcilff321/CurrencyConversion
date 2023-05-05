package org.homework.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.data.FiatCurrencyResponseData;
import org.homework.data.FiatCurrencyUpdateMainRequestData;
import org.homework.data.FiatCurrencyUpdateRequestData;
import org.homework.model.FiatCurrency;
import org.homework.service.FiatCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/fiatCurrency")
@RestController
public class FiatCurrencyController {

    @Autowired
    private FiatCurrencyService fiatCurrencyService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/getFiatCurrency")
    public List<FiatCurrency> getFiatCurrencyRate() {

        log.info("getFiatCurrencyRate");

        return fiatCurrencyService.getFiatCurrency();
    }

    @PostMapping("/createFiatCurrency")
    public String createFiatCurrency(@RequestBody FiatCurrencyUpdateMainRequestData createInfo) {

        try {
            log.info("createFiatCurrency" + objectMapper.writeValueAsString(createInfo));

            fiatCurrencyService.createFiatCurrency(createInfo);
        } catch (Exception e) {
            return "Save error";
        }

        return "Save success";
    }

    @PostMapping("/updateFiatCurrency")
    public String updateFiatCurrency(@RequestBody FiatCurrencyUpdateMainRequestData updateInfo) {

        try {
            log.info("updateFiatCurrency" + objectMapper.writeValueAsString(updateInfo));

            fiatCurrencyService.updateFiatCurrency(updateInfo);
        } catch (Exception e) {
            return "Update error";
        }

        return "Update success";
    }

    @PostMapping("/deleteFiatCurrency")
    public String deleteFiatCurrency(@RequestBody List<String> fiatCodes) {

        try {
            log.info("deleteFiatCurrency");

            fiatCurrencyService.deleteFiatCurrency(fiatCodes);
        } catch (Exception e) {
            return "Delete error";
        }

        return "Delete success";
    }
}
