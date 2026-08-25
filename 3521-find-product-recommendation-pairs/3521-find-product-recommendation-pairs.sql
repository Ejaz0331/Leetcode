with cte as (
    select a.*,b.category
    from productpurchases as a 
    left join productinfo as b
    on a.product_id = b.product_id 
),
cte1 as (   
    select c1.product_id as product1_id ,
        c2.product_id as product2_id ,
        c1.category as product1_category ,
        c2.category as product2_category ,
        c1.user_id 
    from cte as c1
    inner join cte as c2 
    on c1.user_id = c2.user_id 
    where c1.product_id<c2.product_id
    
)
select product1_id , 
    product2_id , 
    product1_category,
    product2_category , 
    count(*) as customer_count 
from cte1
group by product1_id , product2_id 
having customer_count>=3
order by customer_count desc ,product1_id ,product2_id 