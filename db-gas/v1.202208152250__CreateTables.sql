create table gas
(
    id       int UNSIGNED NOT NULL AUTO_INCREMENT,
    date     varchar(255),
    odometer varchar(255),
    cost     varchar(255),
    gas      varchar(255),
    vehicle  varchar(255),
    CONSTRAINT gas_pk PRIMARY KEY (id)
);
