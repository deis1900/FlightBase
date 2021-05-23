INSERT IGNORE INTO air_company(id, name, company_type, founded_at)
VALUES (1, 'FryFly corp.', 'Z.A.O.', '2015-09-29'),
       (2, 'ChinaAir', 'F.O.P.', '2019-03-04');

INSERT IGNORE INTO airplane
(id, name, factory_serial_number, air_company_id, number_of_flights, flight_distance, fuel_capacity, type, created_at)
VALUES (100, 'Boing 737MAX', 'BBJ MAX 9', 2, '232FR', 9820, 11200, 'International', '2017-12-10'),
       (101, 'Airbus A330-900neo', 'A330-900neo', 1, '909PL', 13209, 9800, 'Country', '2016-09-12'),
       (103, 'CRAIC CR929', 'CR929-700', 1, '75wR9', 11000, 11230, 'Continental', '2019-10-11');

INSERT IGNORE INTO flight(id, flight_status, Air_company_id, airplane_id, departure_country, destination_country,
                          distance, estimated_flight_time, ended_at, delay_started_at, created_at)
VALUES (1, 0, 1, 2, 'Ukraine', 'Great Britain', 2133, '03:20', '2021-05-02 00:00:00', '2021-05-01 15:55:00', '2021-05-01 00:40:00'),
       (2, 0, 2, 1, 'Ukraine', 'Great Britain', 2133, '05:20', null, '2021-05-01 15:55:00', null),
       (3, 1, 1, 3, 'Ukraine', 'Great Britain', 2133, '03:50', null, '2017-05-01 15:55:00', null),
       (4, 3, 1, 2, 'Ukraine', 'Great Britain', 2133, '05:20', null, '2020-05-01 15:55:00', null),
       (5, 0, 2, 1, 'Ukraine', 'Great Britain', 2133, '09:45', null, '2017-05-01 15:55:00', null),
       (6, 0, 2, 1, 'Ukraine', 'Great Britain', 2133, '03:55', null, '2017-05-01 15:55:00', null);


