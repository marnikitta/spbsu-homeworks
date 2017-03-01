DROP TABLE status CASCADE CONSTRAINTS;
CREATE TABLE status (
  id   NUMBER PRIMARY KEY,
  name VARCHAR2(10) CHECK ( name IN ('booked', 'started', 'finished') ) UNIQUE
);

DROP TABLE class CASCADE CONSTRAINTS;
CREATE TABLE class (
  id   NUMBER PRIMARY KEY,
  name VARCHAR2(10) CHECK ( name IN ('econom', 'business') ) UNIQUE
);

DROP TABLE payment_type CASCADE CONSTRAINTS;
CREATE TABLE payment_type (
  id   NUMBER PRIMARY KEY,
  name VARCHAR2(10) CHECK ( name IN ('cash', 'card') ) UNIQUE
);

DROP TABLE car CASCADE CONSTRAINTS;
CREATE TABLE car (
  id               NUMBER PRIMARY KEY,
  name             VARCHAR2(128)                                                     NOT NULL,
  class_id         NUMBER REFERENCES class (id)                                      NOT NULL,
  model            VARCHAR2(128)                                                     NOT NULL,
  stars            NUMBER CHECK (stars >= 0 AND stars <= 5)                          NOT NULL,
  color            VARCHAR2(128)                                                     NOT NULL,
  seats            NUMBER                                                            NOT NULL,
  baby_seat        NUMBER CHECK (baby_seat IN (0, 1))                                NOT NULL,
  price_per_minute NUMBER                                                            NOT NULL
);
DROP SEQUENCE car_seq;
CREATE SEQUENCE car_seq START WITH 1;


DROP TABLE booking CASCADE CONSTRAINTS;
CREATE TABLE booking (
  id              NUMBER PRIMARY KEY,
  client_phone    VARCHAR2(128)                                      NOT NULL,
  car_id          NUMBER REFERENCES car (id)                         NOT NULL,
  created_ts      TIMESTAMP DEFAULT (systimestamp)                   NOT NULL,
  approx_ts       TIMESTAMP                                          NOT NULL,
  start_ts        TIMESTAMP,
  finnish_ts      TIMESTAMP,
  status          NUMBER DEFAULT (0) REFERENCES status (id)          NOT NULL,
  payment_type_id NUMBER REFERENCES payment_type (id)                NOT NULL,
  location_from   VARCHAR2(128)                                      NOT NULL,
  location_to     VARCHAR2(128)                                      NOT NULL
);
DROP SEQUENCE booking_seq;
CREATE SEQUENCE booking_seq START WITH 1;

DROP VIEW car_view CASCADE CONSTRAINTS;
CREATE VIEW car_view AS
  SELECT
    car.id,
    car.name,
    class.name AS class,
    car.model,
    car.stars,
    car.color,
    car.seats,
    car.baby_seat,
    car.price_per_minute
  FROM car
    JOIN class ON (car.class_id = class.id);

------

-- noinspection SqlResolveForFile
INSERT INTO status (id, name) VALUES (0, 'booked');
INSERT INTO status (id, name) VALUES (1, 'started');
INSERT INTO status (id, name) VALUES (2, 'finished');

INSERT INTO class (id, name) VALUES (0, 'econom');
INSERT INTO class (id, name) VALUES (1, 'business');

INSERT INTO payment_type (id, name) VALUES (0, 'cash');
INSERT INTO payment_type (id, name) VALUES (1, 'card');
--

--
INSERT INTO car (id, name, class_id, model, stars, color, seats, baby_seat, price_per_minute)
VALUES (car_seq.nextval, 'John', 0, 'Lada-Niva', 3, 'green', 4, 1, 12);

INSERT INTO car (id, name, class_id, model, stars, color, seats, baby_seat, price_per_minute)
VALUES (car_seq.nextval, 'Michael', 1, 'Tesla model 3', 5, 'silver', 2, 0, 100);

INSERT INTO car (id, name, class_id, model, stars, color, seats, baby_seat, price_per_minute)
VALUES (car_seq.nextval, 'Jennifer', 0, 'Telega', 1, 'silver', 1, 0, 1);
--

--
INSERT INTO booking (id, client_phone, car_id, created_ts, approx_ts, start_ts, finnish_ts, status, payment_type_id, location_from, location_to)
VALUES (booking_seq.nextval, '123-32-32', 3, to_timestamp('1997-01-31 09:26:50', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('1997-01-31 10:00:00', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('1997-01-31 10:01:00', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('1997-01-31 10:30:00', 'yyyy-mm-dd hh24:mi:ss'), 2, 1, 'Address 1',
                             'Address 2');

INSERT INTO booking (id, client_phone, car_id, created_ts, approx_ts, start_ts, finnish_ts, status, payment_type_id, location_from, location_to)
VALUES (booking_seq.nextval, '+7(921)123-32-32', 1, to_timestamp('1997-01-31 09:28:50', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('1997-01-31 10:15:00', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('1997-01-31 10:10:00', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('1997-01-31 11:00:00', 'yyyy-mm-dd hh24:mi:ss'), 2, 1, 'Address3',
                             'Address4');

INSERT INTO booking (id, client_phone, car_id, created_ts, approx_ts, start_ts, finnish_ts, status, payment_type_id, location_from, location_to)
VALUES (booking_seq.nextval, '123-32-12', 3, to_timestamp('2016-01-31 09:28:50', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('2016-01-31 10:15:00', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('2016-01-31 10:10:00', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('2016-01-31 11:00:00', 'yyyy-mm-dd hh24:mi:ss'), 2, 1, 'Address4',
                             'Address5');

INSERT INTO booking (id, client_phone, car_id, created_ts, approx_ts, start_ts, finnish_ts, status, payment_type_id, location_from, location_to)
VALUES (booking_seq.nextval, '123-123-123', 2, to_timestamp('2016-01-31 09:28:50', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('2016-01-31 10:15:00', 'yyyy-mm-dd hh24:mi:ss'),
                             to_timestamp('2016-01-31 10:10:00', 'yyyy-mm-dd hh24:mi:ss'),
                             NULL, 1, 1, 'Address5', 'Address8');
--
DROP VIEW location_hint;

CREATE VIEW location_hint AS
  SELECT DISTINCT location
  FROM (SELECT DISTINCT location_from location
        FROM booking
        UNION SELECT DISTINCT location_to location
              FROM booking);

DROP VIEW booking_view;

CREATE VIEW booking_view AS
  SELECT
    booking.id,
    booking.client_phone,
    booking.car_id,
    booking.created_ts,
    booking.approx_ts,
    booking.start_ts,
    booking.finnish_ts,
    status.name       AS status,
    payment_type.name AS payment_type,
    booking.location_from,
    booking.location_to
  FROM booking
    JOIN status ON (booking.status = status.id)
    JOIN payment_type ON (booking.payment_type_id = payment_type.id);

--
SELECT DISTINCT client_phone
FROM booking;

SELECT *
FROM location_hint;

CREATE OR REPLACE TRIGGER insert_booking
BEFORE INSERT ON booking
FOR EACH ROW
  DECLARE
    booking_id NUMBER;
  BEGIN
    SELECT booking_seq.NEXTVAL
    INTO booking_id
    FROM dual;
    :NEW.id := booking_id;
  END;
/

CREATE OR REPLACE TRIGGER insert_car
BEFORE INSERT ON car
FOR EACH ROW
  DECLARE
    car_id NUMBER;
  BEGIN
    SELECT car_seq.NEXTVAL
    INTO car_id
    FROM dual;
    :NEW.id := car_id;
  END;
/
SELECT
  "#OWNER#"."CAR"."ID",
  "#OWNER#"."CAR"."NAME",
  "#OWNER#"."CLASS"."NAME" AS "CLASS",
  "MODEL",
  "STARS",
  "COLOR",
  "SEATS",
  "BABY_SEAT",
  "PRICE_PER_MINUTE"
FROM "#OWNER#"."CAR"
  JOIN "#OWNER#"."CLASS" ON ("#OWNER#"."CAR"."CLASS_ID" = "#OWNER#"."CLASS"."ID");

SELECT
  "CAR_ID" || '(' || "#OWNER#"."STATUS"."NAME" || ')' AS "TITLE",
  "#OWNER#"."BOOKING"."ID",
  "CLIENT_PHONE",
  "CAR_ID",
  "APPROX_TS",
  "START_TS",
  "FINNISH_TS",
  "#OWNER#"."STATUS"."NAME"                           AS "STATUS",
  "#OWNER#"."PAYMENT_TYPE"."NAME"                     AS "PAYMENT_TYPE",
  "LOCATION_FROM",
  "LOCATION_TO"
FROM "#OWNER#"."BOOKING"
  JOIN "#OWNER#"."STATUS" ON ("#OWNER#"."BOOKING"."STATUS" = "#OWNER#"."STATUS"."ID")
  JOIN "#OWNER#"."PAYMENT_TYPE" ON ("#OWNER#"."BOOKING"."PAYMENT_TYPE_ID" = "#OWNER#"."PAYMENT_TYPE"."ID")
  JOIN "#OWNER#"."CAR" ON ("#OWNER#"."BOOKING"."CAR_ID" = "#OWNER#"."CAR"."ID")
WHERE UPPER("#OWNER#"."CAR"."NAME") = APEX_CUSTOM_AUTH.GET_USERNAME
