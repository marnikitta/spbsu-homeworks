CREATE GLOBAL TEMPORARY TABLE result (
  row_id    NUMBER,
  title     VARCHAR2(64),
  january   NUMBER,
  february  NUMBER,
  march     NUMBER,
  april     NUMBER,
  may       NUMBER,
  june      NUMBER,
  july      NUMBER,
  august    NUMBER,
  september NUMBER,
  october   NUMBER,
  november  NUMBER,
  december  NUMBER
) ON COMMIT PRESERVE ROWS;


SELECT *
FROM (
  SELECT
    0                                                AS row_id,
    'First decade'                                   AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(DAY FROM time) BETWEEN 0 AND 10
        AND extract(YEAR FROM time) = 2009
  UNION
  SELECT
    1                                                AS row_id,
    'Second decade'                                  AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(DAY FROM time) BETWEEN 11 AND 20
        AND extract(YEAR FROM time) = 2009
  UNION
  SELECT
    2                                                AS row_id,
    'Third decade'                                   AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(DAY FROM time) BETWEEN 21 AND 31
        AND extract(YEAR FROM time) = 2009
  UNION
  SELECT
    3                                                AS row_id,
    'Sum for 2009'                                   AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(YEAR FROM TIME) = 2009
  UNION
  SELECT
    4                                                AS row_id,
    'First decade'                                   AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(DAY FROM time) BETWEEN 0 AND 10
        AND extract(YEAR FROM time) = 2010
  UNION
  SELECT
    5                                                AS row_id,
    'Second decade'                                  AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(DAY FROM time) BETWEEN 11 AND 20
        AND extract(YEAR FROM time) = 2010
  UNION
  SELECT
    6                                                AS row_id,
    'Third decade'                                   AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(DAY FROM time) BETWEEN 21 AND 31
        AND extract(YEAR FROM time) = 2010
  UNION
  SELECT
    7                                                AS row_id,
    'Sum for 2010'                                   AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(YEAR FROM TIME) = 2010
  UNION
  SELECT
    8                                                AS row_id,
    'Sum for 2009 and 2010'                          AS title,
    SUM(DECODE(extract(MONTH FROM time), 1, value))  AS january,
    SUM(DECODE(extract(MONTH FROM time), 2, value))  AS february,
    SUM(DECODE(extract(MONTH FROM time), 3, value))  AS march,
    SUM(DECODE(extract(MONTH FROM time), 4, value))  AS april,
    SUM(DECODE(extract(MONTH FROM time), 5, value))  AS may,
    SUM(DECODE(extract(MONTH FROM time), 6, value))  AS june,
    SUM(DECODE(extract(MONTH FROM time), 7, value))  AS july,
    SUM(DECODE(extract(MONTH FROM time), 8, value))  AS august,
    SUM(DECODE(extract(MONTH FROM time), 9, value))  AS september,
    SUM(DECODE(extract(MONTH FROM time), 10, value)) AS october,
    SUM(DECODE(extract(MONTH FROM time), 11, value)) AS november,
    SUM(DECODE(extract(MONTH FROM time), 12, value)) AS december
  FROM energy
  WHERE extract(YEAR FROM TIME) IN (2009, 2010)
)
ORDER BY row_id;
