CREATE OR REPLACE VIEW sales_in_month
	AS SELECT product_id, SUM(quantity) AS quantity , DATE_TRUNC('month',  sale_date) AS date_m
                      FROM sale s
                      GROUP BY date_m, product_id
                      ORDER BY date_m ;