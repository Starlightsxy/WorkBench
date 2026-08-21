 select b.bill_date '支付时间',
       CASE b.type
           when 1 then '收入'
           when 2 then '支出'
           else '未知'
           end as  type,
       b.amount    '支付金额',
       b.remark    '支付详情',
       bc.category_name     '支付类别',
       ba.account_name     '支付方式',
       ba.balance  '类别余额',
       bc.icon

from tb_bill b
         inner join tb_bill_category bc on b.category_id = bc.id
         inner join tb_bill_account ba on b.account_id = ba.id
where b.user_id = 1
order by b.create_time desc
limit 5;
