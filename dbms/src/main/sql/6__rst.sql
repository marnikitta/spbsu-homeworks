WITH ema(n, price, ema) AS (
  SELECT
    ROWNUM AS n,
    price  AS price,
    price  AS ema
  FROM (SELECT (open + high + low + close) / 4 AS price
        FROM rts
        ORDER BY time)
  WHERE ROWNUM = 1
  UNION ALL
  SELECT
    r.n                                 AS n,
    r.price                             AS price,
    0.4 * r.price + (1 - 0.4) * ema.ema AS ema
  FROM ema
    JOIN
    (SELECT
       ROWNUM AS n,
       price,
       price  AS ema
     FROM (SELECT (open + high + low + close) / 4 AS price
           FROM rts
           ORDER BY time)
    ) r ON (ema.n = r.n - 1)
)
SELECT *
FROM ema;
