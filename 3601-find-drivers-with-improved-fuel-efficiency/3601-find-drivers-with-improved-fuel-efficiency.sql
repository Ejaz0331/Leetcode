# Write your MySQL query statement below
with cte as (
    select t.driver_id,d.driver_name,
    avg(case when month(trip_date) <=6 then t.distance_km/t.fuel_consumed end ) as first,
    avg(case when month(trip_date) >6 then t.distance_km/t.fuel_consumed  end )as second
    from drivers as d
    join trips as t 
    on d.driver_id=t.driver_id
    group by d.driver_id
      
)select driver_id,driver_name, round(first,2) as first_half_avg,
round(second,2) as second_half_avg,round(second-first,2) as efficiency_improvement 
from cte
where first is not null and second is not null and second>first
order by efficiency_improvement desc,driver_name asc