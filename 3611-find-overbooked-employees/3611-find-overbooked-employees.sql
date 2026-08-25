# Write your MySQL query statement below
with cte as(
    select employee_id,sum(duration_hours) as th from meetings 
    group by employee_id,yearweek(meeting_date,3)
),cte1 as(
    select *,count(*)as ct from cte 
    where th > 20
    group by employee_id
    having count(*)>=2
)
select c1.employee_id,employee_name,department,ct as meeting_heavy_weeks from cte1 c1
join employees e
on c1.employee_id=e.employee_id
order by 4 desc,2;