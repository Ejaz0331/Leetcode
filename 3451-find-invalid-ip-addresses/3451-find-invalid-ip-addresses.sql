with ip_cte as (
    SELECT
        ip,
        SUBSTRING_INDEX(ip, '.', 1) AS o1,
        SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 2), '.', -1) AS o2,
        SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 3), '.', -1) AS o3,
        SUBSTRING_INDEX(ip, '.', -1) AS o4,
        LENGTH(ip) - LENGTH(REPLACE(ip, '.', '')) + 1 AS octet_cnt
    FROM logs
), 
flag_invalid AS (
    SELECT
        ip,
        CASE
            WHEN octet_cnt <> 4 THEN 1
            WHEN CAST(o1 AS UNSIGNED) > 255
              OR CAST(o2 AS UNSIGNED) > 255
              OR CAST(o3 AS UNSIGNED) > 255
              OR CAST(o4 AS UNSIGNED) > 255
            THEN 1
            WHEN (LENGTH(o1) > 1 AND o1 LIKE '0%')
              OR (LENGTH(o2) > 1 AND o2 LIKE '0%')
              OR (LENGTH(o3) > 1 AND o3 LIKE '0%')
              OR (LENGTH(o4) > 1 AND o4 LIKE '0%')
            THEN 1
            ELSE 0
        END AS is_invalid
    FROM ip_cte
)
SELECT
    ip,
    COUNT(*) AS invalid_count
FROM flag_invalid
WHERE is_invalid = 1
GROUP BY ip
ORDER BY invalid_count DESC, ip DESC;