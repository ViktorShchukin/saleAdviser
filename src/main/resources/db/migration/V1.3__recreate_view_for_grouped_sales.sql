CREATE OR REPLACE VIEW sales_in_month_new
	AS SELECT ROW_NUMBER() OVER () AS id, product_id, sum(quantity) AS quantity , date_trunc('month',  sale_date) AS date_m
                      FROM sale s
                      GROUP BY date_m, product_id
                      ORDER BY date_m ;