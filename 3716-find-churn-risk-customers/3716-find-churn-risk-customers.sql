WITH cte AS (
    SELECT
        user_id,
        MAX(event_date),
        MAX(monthly_amount) AS max_historical_amount,
        COALESCE(
            SUM(CASE WHEN event_type = 'downgrade' THEN 1 END)
        , 0) AS downgrade_count,
        DATEDIFF(MAX(event_date), MIN(event_date)) AS days_as_subscriber
    FROM subscription_events
    GROUP BY user_id
),
rankings AS (
    SELECT *,
        ROW_NUMBER() OVER(PARTITION BY user_id 
        ORDER BY event_date DESC) AS last
    FROM subscription_events
)
SELECT
    c.user_id,
    r.plan_name AS current_plan,
    r.monthly_amount AS current_monthly_amount,
    c.max_historical_amount,
    c.days_as_subscriber
FROM cte c
JOIN rankings r
    ON r.user_id = c.user_id
WHERE 
    last = 1 AND r.event_type <> 'cancel' AND 
    c.downgrade_count >= 1 AND
    r.monthly_amount/c.max_historical_amount < 0.5 AND
    c.days_as_subscriber >= 60
ORDER BY 5 DESC, 1;
    
