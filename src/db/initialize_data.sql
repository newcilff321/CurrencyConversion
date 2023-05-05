INSERT INTO `hw`.`fiat_currency` (`code`, `symbol`, `description`,`zh_name`) VALUES
    ('USD', '&#36;', 'United States Dollar', '美元'),
    ('GBP', '&pound;', 'British Pound Sterling', '英鎊'),
    ('EUR', '&euro;', 'Euro', '歐元');

INSERT INTO `hw`.`crypto_currency` (`code`) VALUES ('Bitcoin');

INSERT INTO `hw`.`exchange_rate` (`fiat_currency_id`, `crypto_currency_id`, `rate_float`) VALUES
    ('1', '1', '28214.3518'),
    ('2', '1', '23575.6866'),
    ('3', '1', '27484.8979');
