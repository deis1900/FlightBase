drop table if exists flight;
drop table if exists airplane;
drop table if exists air_company;

CREATE TABLE air_company
(
    id           BIGINT   NOT NULL AUTO_INCREMENT,
    company_type varchar(255) DEFAULT NULL,
    founded_at   date         NOT NULL,
    name         varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY `UK_8dtowp4c00cgujvdnj9mhab82` (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE airplane
(
    id                    BIGINT   NOT NULL AUTO_INCREMENT,
    created_at            date         NOT NULL,
    factory_serial_number varchar(255) NOT NULL,
    flight_distance       int(11)    DEFAULT NULL,
    fuel_capacity         int(11)      NOT NULL,
    name                  varchar(255) NOT NULL,
    number_of_flights     varchar(255) NOT NULL,
    type                  varchar(255) NOT NULL,
    air_company_id        bigint DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY `UK_lb8i0o8ixn350ngcee1tmcnjp` (factory_serial_number),
    UNIQUE KEY `UK_mttpjklvtuecfynvjhavfenc9` (number_of_flights)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE flight
(
    id                    BIGINT   not null AUTO_INCREMENT,
    created_at            DATETIME,
    delay_started_at      DATETIME,
    departure_country     varchar(255) not null,
    destination_country   varchar(255) not null,
    distance              integer      not null,
    ended_at              DATETIME,
    estimated_flight_time TIME         not null,
    flight_status         varchar(255) not null,
    air_company_id        bigint,
    airplane_id           bigint,
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

ALTER TABLE airplane
    ADD KEY `FKp9yob98tmyb901cnu35k9uy43` (air_company_id);
ALTER TABLE airplane
    ADD CONSTRAINT `FKp9yob98tmyb901cnu35k9uy43` FOREIGN KEY (air_company_id) REFERENCES air_company (id);

ALTER TABLE flight
    ADD KEY fk_airplane_flight_foreign (air_company_id);
ALTER TABLE flight
    ADD CONSTRAINT fk_airplane_flight_foreign FOREIGN KEY (air_company_id) REFERENCES air_company (id) ON DELETE CASCADE;

ALTER TABLE flight
    ADD KEY FKebvst1vfvhmhgn73mqs1vq90w (airplane_id);
ALTER TABLE flight
    ADD CONSTRAINT FKebvst1vfvhmhgn73mqs1vq90w FOREIGN KEY (airplane_id) REFERENCES airplane (id) ON DELETE CASCADE;
