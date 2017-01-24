DROP TABLE energy;

CREATE TABLE energy (
  value NUMBER,
  time  TIMESTAMP
);

INSERT INTO energy
  SELECT
    value                                            AS value,
    to_timestamp(period, 'DD.MM.YYYY HH24:MI:SS:FF') AS time
  FROM ener;


WITH tmp AS (
    SELECT
      sum(value)               AS value,
      min(to_char(time, 'MM')) AS month,
      min(to_char(time, 'DY')) AS dayofweek,
      min(to_char(time, 'D'))  AS dayofweekno
    FROM energy
    GROUP BY to_char(time, 'DD.MM.YYYY')
)
SELECT
  NULL        link,
  w.dayofweek label,
  winter,
  spring,
  summer,
  fall
FROM (SELECT
        dayofweek,
        dayofweekno,
        avg(value) winter
      FROM tmp
      WHERE month IN (1, 2, 12)
      GROUP BY dayofweek, dayofweekno) w
  JOIN (SELECT
          dayofweek,
          dayofweekno,
          avg(value) spring
        FROM tmp
        WHERE month IN (3, 4, 5)
        GROUP BY dayofweek, dayofweekno) s
    ON (w.dayofweek = s.dayofweek)
  JOIN (SELECT
          dayofweek,
          dayofweekno,
          avg(value) summer
        FROM tmp
        WHERE month IN (6, 7, 8)
        GROUP BY dayofweek, dayofweekno) su
    ON (w.dayofweek = su.dayofweek)
  JOIN (SELECT
          dayofweek,
          dayofweekno,
          avg(value) fall
        FROM tmp
        WHERE month IN (9, 10, 11)
        GROUP BY dayofweek, dayofweekno) f
    ON (w.dayofweek = f.dayofweek)
ORDER BY w.dayofweekno;
