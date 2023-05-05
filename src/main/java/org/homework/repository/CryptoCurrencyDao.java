package org.homework.repository;

import org.homework.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoCurrencyDao extends JpaRepository<CryptoCurrency, Long> {
    @Query(value = "SELECT cc.* FROM hw.crypto_currency cc " +
            "WHERE cc.code = ?"
            , nativeQuery = true)
    Optional<CryptoCurrency> getCryptoCurrencyByCode(String code);
}
