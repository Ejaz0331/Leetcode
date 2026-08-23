# Write your MySQL query statement below
select s.user_id , 
    ifnull(round(sum(action='confirmed')/count(s.user_id),2),0) as confirmation_rate 
from signups as s
left join confirmations as c on s.user_id=c.user_id
group by user_id 