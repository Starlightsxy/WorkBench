explain SELECT COALESCE(SUM(balance), 0)
FROM tb_bill_account
WHERE user_id = 1
  AND deleted = 0;
