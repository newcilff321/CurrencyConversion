USE hw;

-- -----------------------------------------------------
-- Table `fiat_currency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS fiat_currency (
    id BIGINT NOT NULL AUTO_INCREMENT,
    code VARCHAR(32) NOT NULL,
    symbol VARCHAR(32) NOT NULL,
    description VARCHAR(100) NOT NULL,
    zh_name VARCHAR(100) NOT NULL,
    creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE INDEX `fiat_currency_code_UNIQUE` (`code` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `crypto_currency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS crypto_currency (
    id BIGINT NOT NULL AUTO_INCREMENT,
    code VARCHAR(32) NOT NULL,
    creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE INDEX `crypto_currency_code_UNIQUE` (`code` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `exchange_rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS exchange_rate (
    id BIGINT NOT NULL AUTO_INCREMENT,
    fiat_currency_id BIGINT NOT NULL,
    crypto_currency_id BIGINT NOT NULL,
    rate_float DECIMAL(10, 4) NOT NULL,
    creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_fiat_currency_exchange_rate1
        FOREIGN KEY (fiat_currency_id)
        REFERENCES fiat_currency (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_crypto_currency_exchange_rate1
        FOREIGN KEY (crypto_currency_id)
        REFERENCES crypto_currency (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

