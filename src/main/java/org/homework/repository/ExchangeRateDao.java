package org.homework.repository;

import org.homework.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateDao extends JpaRepository<ExchangeRate, Long> {
    @Query(value = "SELECT er.* FROM hw.fiat_currency fc " +
            "INNER JOIN hw.exchange_rate er ON er.fiat_currency_id = fc.id "
            , nativeQuery = true)
    List<ExchangeRate> getExistRate();
}
