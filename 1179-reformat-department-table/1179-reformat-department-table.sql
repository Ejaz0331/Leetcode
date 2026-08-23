# Write your MySQL query statement below
SELECT id ,
sum(if(month='jan',revenue,Null)) as Jan_Revenue,
sum(if(month='Feb',revenue,Null)) as Feb_Revenue,
sum(if(month='Mar',revenue,Null)) as Mar_Revenue,
sum(if(month='Apr',revenue,Null)) as Apr_Revenue,
sum(if(month='May',revenue,Null)) as May_Revenue,
sum(if(month='Jun',revenue,Null)) as Jun_Revenue,
sum(if(month='Jul',revenue,Null)) as Jul_Revenue,
sum(if(month='Aug',revenue,Null)) as Aug_Revenue,
sum(if(month='Sep',revenue,Null)) as Sep_Revenue,
sum(if(month='Oct',revenue,Null)) as Oct_Revenue,
sum(if(month='Nov',revenue,Null)) as Nov_Revenue,
sum(if(month='Dec',revenue,Null)) as Dec_Revenue 
from Department 
group by id  ;